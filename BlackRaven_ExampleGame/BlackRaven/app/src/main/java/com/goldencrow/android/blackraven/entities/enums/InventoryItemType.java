package com.goldencrow.android.blackraven.entities.enums;

/**
 * @author Philipp Hermüller
 * @version 14.11.2017
 */

public enum InventoryItemType {
    // The item types with their size
    SINGLE_ITEM(1), GENRE(3);



    // the size of the item.
    private int size;

    /**
     * create new item with specific size.
     *
     * @param size of the inventoryItem.
     */
    InventoryItemType(int size) {
        this.size = size;
    }

    /**
     *
     * @return the size of the item
     */
    public int getItemSize() {
        return size;
    }
}
