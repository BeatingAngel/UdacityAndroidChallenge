package com.goldencrow.android.bookinventory.entity.enums;

/**
 * @author Philipp Hermüller
 * @version 12.11.2017
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
