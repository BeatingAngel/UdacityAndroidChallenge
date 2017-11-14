package com.goldencrow.android.bookinventory.entity;

import com.goldencrow.android.bookinventory.entity.enums.BookType;

/**
 * @author Philipp Hermüller
 * @version 12.11.2017
 */

public class Book {

    private BookType type;
    private String name;
    private String imageUrl;

    public Book(BookType type, String name, String imageUrl) {
        this.type = type;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    //region Getter-Setter

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //endregion
}
