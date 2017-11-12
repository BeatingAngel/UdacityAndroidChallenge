package com.goldencrow.android.bookinventory.utilities;

import android.net.Uri;
import android.util.Log;

import com.goldencrow.android.bookinventory.entity.Book;
import com.goldencrow.android.bookinventory.entity.enums.BookType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Philipp Hermüller
 * @version 12.11.2017
 */

public class BookInventoryUtility {

    private static final String TAG = BookInventoryUtility.class.getSimpleName();

    private static final Uri BASE_API_NOVEL_URI =
            Uri.parse("http://gravitytales.com/api/novels/");

    /**
     * Return a list of all books from the website GravityTales.
     * (This also includes the GenreTitles)
     *
     * @return  a list of all books (and genreTitles).
     */
    public static List<Book> getWebNovelsFromGravityTales() {
        try {
            // URL to the API which return all books.
            URL apiURL = new URL(BASE_API_NOVEL_URI.toString());
            // get the response...
            String jsonResponse = getResponseFromHttpUrl(apiURL);
            // ... and parse that json into a list of books.
            List<Book> books = parseJsonToBook(jsonResponse);
            // Add genre-titles into the list and return it.
            return addGenreTitlesTo(books);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG, "Could not convert Uri to URL!");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Could not read json from api.");
            return null;
        }
    }

    /**
     * This is the code from the Udacity-course.
     *
     * Read the json-data from the response and build a string of it.
     *
     * @param url   from where the data should be fetched.
     * @return  the json-data as a string.
     *
     * @throws IOException  if the data couldn't be read.
     *  The exception will be handled in the callerFunction in order to have all error-Messages
     *  at one place.
     */
    private static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        } finally {
            urlConnection.disconnect();
        }
    }

    /**
     * Convert the jsonString into a jsonObject and store its values in a list in the form
     * of books.
     *
     * @param json  which shall be converted into a list of books.
     * @return  a list of books.
     */
    private static List<Book> parseJsonToBook(String json) {

        try {
            List<Book> books = new LinkedList<>();

            JSONArray listOfBooks = new JSONArray(json);
            for (int i = 0; i < listOfBooks.length(); i++) {
                JSONObject book = listOfBooks.getJSONObject(i);

                // create a new book with those values.
                Book novelBook = new Book(
                        BookType.BOOK_ITEM,
                        book.getInt("Id"),
                        book.getString("Name"),
                        book.getString("CoverUrl"),
                        book.getString("Slug")
                );

                // ...add it into the list.
                books.add(novelBook);
            }

            return books;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Could not parse jsonString to JSONArray Object.");
            return null;
        }
    }

    /**
     * This is optional!!
     * It inserts genreTitles into the list.
     * <p>
     * Because the books haven't genre-tags on them, the genre is now the first character.
     *
     * @param books     a list of books (nothing special).
     * @return  the modified list where genre-titles are present.
     */
    private static List<Book> addGenreTitlesTo(List<Book> books) {
        char actualGenre = ' ';
        for (int i = 0; i < books.size(); i++) {
            Book actualBook = books.get(i);
            // if the first character of the title is different than the one before -> new GenreTitle
            if (actualBook.getName().charAt(0) != actualGenre) {
                actualGenre = actualBook.getName().charAt(0);
                books.add(i,
                        new Book(
                                BookType.GENRE_TITLE,
                                -1,
                                "GENRE: " + actualGenre,
                                "no Image",
                                "no slug"
                                )
                );
            }
        }
        return books;
    }

}