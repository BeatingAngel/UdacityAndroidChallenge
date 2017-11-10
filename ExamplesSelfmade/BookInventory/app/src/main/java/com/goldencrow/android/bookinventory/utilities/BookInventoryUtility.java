package com.goldencrow.android.bookinventory.utilities;

import com.goldencrow.android.bookinventory.R;
import com.goldencrow.android.bookinventory.entity.Book;
import com.goldencrow.android.bookinventory.entity.BookType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Philipp on 09.11.17.
 */

public class BookInventoryUtility {

    public static List<Book> getMockBookList() {
        List<Book> gameItems = new LinkedList<>();

        gameItems.add(
                new Book(BookType.GENRE_TITLE, "Romance", 0)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "The Marriage", R.color.oceanBlue)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "The Love Triangle", R.color.skyBlue)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "Nations best lover", R.color.darkBlue)
        );
        gameItems.add(
                new Book(BookType.GENRE_TITLE, "Horror", 0)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "Golden Crow", R.color.skyBlue)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "Black Raven", R.color.darkBlue)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "Red Vulture", R.color.bloodRed)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "Little Freak", R.color.darkBlue)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "Crazy Owl", R.color.skyBlue)
        );
        gameItems.add(
                new Book(BookType.GENRE_TITLE, "Action", 0)
        );
        gameItems.add(
                new Book(BookType.BOOK_ITEM, "Night Ranger", R.color.skyBlue)
        );

        return gameItems;
    }

}