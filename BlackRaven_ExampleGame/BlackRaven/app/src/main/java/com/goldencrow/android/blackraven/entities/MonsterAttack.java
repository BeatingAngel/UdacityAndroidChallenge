package com.goldencrow.android.blackraven.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Philipp Hermüller
 * @version 15.11.2017
 */

public class MonsterAttack implements Parcelable {

    private String name;
    private int damageValue;
    private int maxUsages;
    private int remainingUsages;

    public MonsterAttack(String name, int damageValue, int maxUsages) {
        this.name = name;
        this.damageValue = damageValue;
        this.maxUsages = maxUsages;
        this.remainingUsages = maxUsages;
    }

    protected MonsterAttack(Parcel in) {
        name = in.readString();
        damageValue = in.readInt();
        maxUsages = in.readInt();
        remainingUsages = in.readInt();
    }

    public String getName() {
        return name;
    }

    public int getDamageValue() {
        return damageValue;
    }

    public int getMaxUsages() {
        return maxUsages;
    }

    public int getRemainingUsages() {
        return remainingUsages;
    }

    public int decreaseUsages() {
        this.remainingUsages -= 1;
        return remainingUsages;
    }

    public static final Creator<MonsterAttack> CREATOR = new Creator<MonsterAttack>() {
        @Override
        public MonsterAttack createFromParcel(Parcel in) {
            return new MonsterAttack(in);
        }

        @Override
        public MonsterAttack[] newArray(int size) {
            return new MonsterAttack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(damageValue);
        parcel.writeInt(maxUsages);
        parcel.writeInt(remainingUsages);
    }
}
