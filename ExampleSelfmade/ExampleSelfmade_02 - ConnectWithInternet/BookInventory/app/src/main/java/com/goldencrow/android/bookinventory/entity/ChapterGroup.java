package com.goldencrow.android.bookinventory.entity;

/**
 * @author Philipp Hermüller
 * @version 12.11.2017
 */

public class ChapterGroup {

    private int _id;
    private double fromChapter;
    private int novelApiId;
    private int orderIndex;
    private String title;

    public ChapterGroup(int _id, double fromChapter, int novelApiId, int orderIndex, String title) {
        this._id = _id;
        this.fromChapter = fromChapter;
        this.novelApiId = novelApiId;
        this.orderIndex = orderIndex;
        this.title = title;
    }

    //region Getter

    public int get_id() {
        return _id;
    }

    public double getFromChapter() {
        return fromChapter;
    }

    public int getNovelApiId() {
        return novelApiId;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public String getTitle() {
        return title;
    }

    //endregion
}
