package com.goldencrow.android.bookinventory.entity;

import com.goldencrow.android.bookinventory.entity.enums.BookType;

/**
 * @author Philipp Hermüller
 * @version 12.11.2017
 */

public class Book {

    private BookType type;
    private int apiId;
    private String name;
    private String imageName;
    private String slug;

    public Book(BookType type, int apiId, String name, String imageName, String slug) {
        this.type = type;
        this.apiId = apiId;
        this.name = name;
        this.imageName = imageName;
        this.slug = slug;
    }

    //region Getter-Setter

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    //endregion
}
