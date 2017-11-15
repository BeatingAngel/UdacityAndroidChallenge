package com.goldencrow.android.blackraven;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.goldencrow.android.blackraven.entities.InventoryItem;
import com.goldencrow.android.blackraven.utilities.InventoryUtility;

public class InventoryActivity extends AppCompatActivity
        implements InventoryAdapter.InventoryAdapterOnClickHandler {

    boolean PARENT_REQUESTS_RESULT;

    RecyclerView mRvInventory;

    InventoryAdapter mInventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Intent intent = getIntent();
        PARENT_REQUESTS_RESULT = intent.getBooleanExtra("result", false);

        mRvInventory = findViewById(R.id.rv_inventory);

        // at the beginning a starterPack is given into the inventory.
        // in the future the inventory will be in the DB so that item can be deleted and added.
        // This way, the items can be remembered even if we close the inventoryActivity.
        mInventoryAdapter = new InventoryAdapter(
                this,
                InventoryUtility.getStarterInventory()
        );

        // how many items in the inventory should make up one row?
        // this could be set and loaded from the preferences.
        int numberOfColumns = 3;

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);

        // tells the layoutManager how many columns a specific item uses in its grid.
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mInventoryAdapter.getItemViewType(position);
            }
        });

        mRvInventory.setLayoutManager(layoutManager);

        mRvInventory.setAdapter(mInventoryAdapter);
    }

    /**
     * Close the inventory.
     *
     * @param view  which called the function to close the inventory.
     */
    public void close_inventory(View view) {
        // the activity is finished and the previous activity will be displayed again.

        Intent resultData = new Intent();
        setResult(Activity.RESULT_CANCELED, resultData);

        finish();
    }

    @Override
    public void ItemOnClick(InventoryItem item) {
        Intent resultData = new Intent();
        resultData.putExtra("item", item);
        setResult(Activity.RESULT_OK, resultData);

        finish();
    }
}
