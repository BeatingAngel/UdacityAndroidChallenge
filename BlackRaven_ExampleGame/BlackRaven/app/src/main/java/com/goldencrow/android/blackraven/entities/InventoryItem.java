package com.goldencrow.android.blackraven.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.goldencrow.android.blackraven.entities.enums.InventoryItemType;

/**
 * @author Philipp Hermüller
 * @version 14.11.2017
 */

public class InventoryItem implements Parcelable {

    private String name;
    private InventoryItemType type;

    public InventoryItem(String name, InventoryItemType type) {
        this.name = name;
        this.type = type;
    }

    protected InventoryItem(Parcel in) {
        name = in.readString();
    }

    public static final Creator<InventoryItem> CREATOR = new Creator<InventoryItem>() {
        @Override
        public InventoryItem createFromParcel(Parcel in) {
            return new InventoryItem(in);
        }

        @Override
        public InventoryItem[] newArray(int size) {
            return new InventoryItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public InventoryItemType getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
