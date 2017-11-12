package com.goldencrow.android.bookinventory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldencrow.android.bookinventory.entity.Book;
import com.goldencrow.android.bookinventory.entity.enums.BookType;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Philipp Hermüller
 * @version 12.11.2017
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.BookViewHolder> {

    private static final String TAG = InventoryAdapter.class.getSimpleName();

    List<Book> mBooks;
    MainActivity mMainActivity;

    public InventoryAdapter(List<Book> books, Context context) {
        this.mBooks = books;
        this.mMainActivity = (MainActivity)context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForBookType =
                viewType == BookType.BOOK_ITEM.getItemSize()
                        ? R.layout.inventory_list_book_item
                        : R.layout.inventory_list_book_genre;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForBookType, parent, false);

        view.setTag(layoutIdForBookType);

        return new BookViewHolder(view);
    }

    /**
     * Fill the viewItems with values.
     *
     * @param bookViewHolder    has all the viewItems in it.
     * @param position          in the dataSet-list.
     */
    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, int position) {
        Book book = mBooks.get(position);

        bookViewHolder.itemText.setText(book.getName());

        if (book.getType() == BookType.BOOK_ITEM) {
            new BookCoverAsyncTask().execute(
                    new BookCoverAsyncTaskParameters(bookViewHolder.bookCover, book, mMainActivity)
            );
        }
    }

    @Override
    public int getItemCount() {
        return mBooks == null ? 0 : mBooks.size();
    }

    /**
     * What: says what type the item in the list from the position is.
     * Why: Some items need to be displayed with another width (colspan).
     *      For example the genre-title.
     *
     * @param position  the position in the dataSet-List.
     * @return  the type of the item.
     */
    @Override
    public int getItemViewType(int position) {
        return mBooks.get(position).getType().getItemSize();
    }

    /**
     * set the new books as the new dataSet.
     * also notify that the dataSet changed.
     *
     * @param books     the new books to display.
     */
    public void setBooks(List<Book> books) {
        mBooks = books;
        notifyDataSetChanged();
    }

    /**
     * a private class which holds all viewItem-variables for a item in the list.
     */
    class BookViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;
        ImageView bookCover;
        int layoutId;

        private BookViewHolder(View itemView) {
            super(itemView);

            layoutId = (int)itemView.getTag();

            // get the appropriate viewItems from the layout.
            if (layoutId == R.layout.inventory_list_book_item) {
                itemText = itemView.findViewById(R.id.tv_bookTitle);
                bookCover = itemView.findViewById(R.id.iv_bookCover);
            } else {
                itemText = itemView.findViewById(R.id.tv_genreTitle);
            }
        }

    }

    /**
     * What: A class for all parameters for the BookCoverAsyncTask.
     * <p>
     * Why:
     * This class is needed to parse multiple parameters of different data-type to the asyncTask.
     * <p>
     * ALSO: Because every variable that the task uses are in here, the asyncTask-class can be
     * made STATIC which means that no leaks can occur (hopefully :D ).
     */
    class BookCoverAsyncTaskParameters {
        ImageView bookCover;
        Book actualBook;
        MainActivity mainActivity;

        private BookCoverAsyncTaskParameters(ImageView bookCover, Book actualBook, MainActivity mainActivity) {
            this.bookCover = bookCover;
            this.actualBook = actualBook;
            this.mainActivity = mainActivity;
        }
    }

    /**
     * What: This task handles the book covers and displays them.
     * Why: Because we get the image from a URL (internet) we can't do that on the main thread.
     */
    static class BookCoverAsyncTask extends AsyncTask<BookCoverAsyncTaskParameters, Void, Void> {

        @Override
        protected Void doInBackground(BookCoverAsyncTaskParameters... parameters) {
            final ImageView bookCover = parameters[0].bookCover;
            Book book = parameters[0].actualBook;
            MainActivity mainActivity = parameters[0].mainActivity;

            URL url = null;
            try {
                if (book.getImageName().equals("cover.png")) {
                    // force into catch to display default cover.
                    throw new IOException("There is no cover.");
                }

                url = new URL("https://cdn.gravitytales.com/images/covers/" + book.getImageName());
                final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                // runOnUiThread() is needed because a view can only be handled by its creator.
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bookCover.setImageBitmap(bmp);
                    }
                });
            } catch (IOException e) {
                Log.e(TAG, "Couldn't display book cover!");
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bookCover.setImageResource(R.drawable.ic_launcher_background);
                    }
                });
            }

            return null;
        }
    }
}
