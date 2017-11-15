package com.goldencrow.android.blackraven.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.goldencrow.android.blackraven.entities.enums.MonsterType;

import java.util.EnumSet;

/**
 * @author Philipp Hermüller
 * @version 14.11.2017
 */

public class Monster implements Parcelable {

    private String mName;
    private int mImage;
    private int mMaxHealth;
    private int mCurrentHealth;
    private MonsterAttack[] mAttacks;
    private EnumSet<MonsterType> mTypes;

    public Monster(String name, int image, int maxHealth, MonsterAttack[] attacks, EnumSet<MonsterType> types) {
        this.mName = name;
        this.mImage = image;
        this.mMaxHealth = maxHealth;
        this.mCurrentHealth = maxHealth;
        this.mAttacks = attacks;
        this.mTypes = types;
    }

    //region Getter

    public String getName() {
        return mName;
    }

    public int getImage() {
        return mImage;
    }

    public int getMaxHealth() {
        return mMaxHealth;
    }

    public int getCurrentHealth() {
        return mCurrentHealth;
    }

    public MonsterAttack[] getAttacks() {
        return mAttacks;
    }

    public EnumSet<MonsterType> getTypes() {
        return mTypes;
    }

    //endregion

    public void takeDamage(int damageValue) {
        this.mCurrentHealth -= damageValue;
        if (mCurrentHealth < 0) {
            mCurrentHealth = 0;
        }
    }

    public void heal(int value) {
        this.mCurrentHealth += value;
        if (mCurrentHealth > mMaxHealth) {
            mCurrentHealth = mMaxHealth;
        }
    }

    public void decreaseAttackCounter(int pos) {
        mAttacks[pos].decreaseUsages();
    }

    //region Parcelable functions

    protected Monster(Parcel in) {
        mName = in.readString();
        mImage = in.readInt();
        mMaxHealth = in.readInt();
        mCurrentHealth = in.readInt();
        mAttacks = in.createTypedArray(MonsterAttack.CREATOR);
        mTypes = (EnumSet<MonsterType>) in.readSerializable();
    }

    public static final Creator<Monster> CREATOR = new Creator<Monster>() {
        @Override
        public Monster createFromParcel(Parcel in) {
            return new Monster(in);
        }

        @Override
        public Monster[] newArray(int size) {
            return new Monster[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeInt(mImage);
        parcel.writeInt(mMaxHealth);
        parcel.writeInt(mCurrentHealth);
        parcel.writeTypedArray(mAttacks, 0);
        parcel.writeSerializable(mTypes);
    }

    //endregion
}
