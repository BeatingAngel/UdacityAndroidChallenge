package com.goldencrow.android.bookinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goldencrow.android.bookinventory.entity.Book;
import com.goldencrow.android.bookinventory.entity.BookType;

import java.util.List;

/**
 * Created by Philipp on 09.11.17.
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.BookViewHolder> {

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

    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, int position) {
        Book book = mBooks.get(position);

        bookViewHolder.itemText.setText(book.getName());

        ViewParent bookParent = bookViewHolder.itemText.getParent();
        FrameLayout bookItemView;
        if (bookParent instanceof FrameLayout) {
            bookItemView = (FrameLayout) bookParent;
            bookItemView.setBackgroundResource(book.getColorCode());
        }
    }

    @Override
    public int getItemCount() {
        return mBooks == null ? 0 : mBooks.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mBooks.get(position).getSize().getItemSize();
    }

    @Override
    public void onViewDetachedFromWindow(BookViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        if (holder.layoutId == R.layout.inventory_list_book_genre) {
            mMainActivity.setTitle(holder.itemText.getText().toString());
        }
    }

    @Override
    public void onViewAttachedToWindow(BookViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        if (holder.layoutId == R.layout.inventory_list_book_genre) {
            Toast.makeText(
                    mMainActivity,
                    holder.itemText.getText() + " is coming!",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;
        int layoutId;

        public BookViewHolder(View itemView) {
            super(itemView);

            layoutId = (int)itemView.getTag();

            if (layoutId == R.layout.inventory_list_book_item) {
                itemText = itemView.findViewById(R.id.tv_bookTitle);
            } else {
                itemText = itemView.findViewById(R.id.tv_genreTitle);
            }
        }

    }
}
