package com.goldencrow.android.bookinventory.entity;

/**
 * Created by Philipp on 09.11.17.
 */

public enum BookType {
    BOOK_ITEM(1), GENRE_TITLE(3);

    private int itemSize;

    BookType(int itemSize) {
        this.itemSize = itemSize;
    }

    public int getItemSize() {
        return itemSize;
    }
}
