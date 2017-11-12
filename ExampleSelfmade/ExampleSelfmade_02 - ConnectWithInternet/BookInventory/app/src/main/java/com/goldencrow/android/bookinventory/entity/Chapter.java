package com.goldencrow.android.bookinventory.entity;

/**
 * @author Philipp Hermüller
 * @version 12.11.2017
 */

public class Chapter {

    private int _id;
    private double number;
    private String name;
    private String slug;

    public Chapter(int _id, double number, String name, String slug) {
        this._id = _id;
        this.number = number;
        this.name = name;
        this.slug = slug;
    }

    //region Getter

    public int get_id() {
        return _id;
    }

    public double getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    //endregion
}
