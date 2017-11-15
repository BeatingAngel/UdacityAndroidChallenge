package com.goldencrow.android.blackraven.utilities;

import com.goldencrow.android.blackraven.entities.InventoryItem;
import com.goldencrow.android.blackraven.entities.enums.InventoryItemType;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Philipp Hermüller
 * @version 14.11.2017
 */

public class InventoryUtility {

    /**
     * Just the starter inventory.
     * Nothing special.
     *
     * @return the entire inventory of a new monster.
     */
    public static List<InventoryItem> getStarterInventory() {
        List<InventoryItem> items = new LinkedList<>();

        items.add(
                new InventoryItem("Potions", InventoryItemType.GENRE)
        );
        items.add(
                new InventoryItem("Small Health Potion", InventoryItemType.SINGLE_ITEM)
        );
        items.add(
                new InventoryItem("Small Health Potion", InventoryItemType.SINGLE_ITEM)
        );
        items.add(
                new InventoryItem("Small Health Potion", InventoryItemType.SINGLE_ITEM)
        );
        items.add(
                new InventoryItem("Medium Health Potion", InventoryItemType.SINGLE_ITEM)
        );

        items.add(
                new InventoryItem("Spells", InventoryItemType.GENRE)
        );
        items.add(
                new InventoryItem("None", InventoryItemType.SINGLE_ITEM)
        );

        items.add(
                new InventoryItem("Talismans", InventoryItemType.GENRE)
        );
        items.add(
                new InventoryItem("None", InventoryItemType.SINGLE_ITEM)
        );

        items.add(
                new InventoryItem("Summons", InventoryItemType.GENRE)
        );
        items.add(
                new InventoryItem("None", InventoryItemType.SINGLE_ITEM)
        );

        return items;
    }

}
