package com.goldencrow.android.blackraven.entities.enums;

/**
 * @author Philipp Hermüller
 * @version 15.11.2017
 */

public enum MonsterType {
    GHOST(-1), SHADOW(0), COLORFUL(42), BIRD(20);

    // a representative value
    private int value;

    /**
     * create new MonsterType.
     *
     * @param value of the type.
     */
    MonsterType(int value) {
        this.value = value;
    }

    /**
     *
     * @return the value of the monsterType.
     */
    public int getValue() {
        return value;
    }
}
