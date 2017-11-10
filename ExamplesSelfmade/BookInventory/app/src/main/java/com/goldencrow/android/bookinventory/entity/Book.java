package com.goldencrow.android.bookinventory.entity;

/**
 * Created by Philipp on 09.11.17.
 */

public class Book {

    private BookType size;
    private String name;
    private int colorCode;

    public Book(BookType size, String name, int colorCode) {
        this.size = size;
        this.name = name;
        this.colorCode = colorCode;
    }

    //region Getter-Setter

    public BookType getSize() {
        return size;
    }

    public void setSize(BookType size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    //endregion
}
