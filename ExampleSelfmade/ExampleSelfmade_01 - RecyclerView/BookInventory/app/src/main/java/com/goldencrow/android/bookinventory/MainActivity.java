package com.goldencrow.android.bookinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.goldencrow.android.bookinventory.utilities.BookInventoryUtility;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mGameInventory;
    private InventoryAdapter mInventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameInventory = findViewById(R.id.rv_game_items);

        mInventoryAdapter = new InventoryAdapter(BookInventoryUtility.getMockBookList(), this);

        int numberOfColumns = 3;
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mInventoryAdapter.getItemViewType(position);
            }
        });

        mGameInventory.setLayoutManager(layoutManager);

        mGameInventory.setAdapter(mInventoryAdapter);
    }
}
