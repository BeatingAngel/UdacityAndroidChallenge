package com.goldencrow.android.bookinventory;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.goldencrow.android.bookinventory.entity.Book;
import com.goldencrow.android.bookinventory.utilities.BookInventoryUtility;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvBookInventory;
    private InventoryAdapter mInventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvBookInventory = findViewById(R.id.rv_book_inventory);

        // at the beginning there are no books. Those will be fetched later in an AsyncTask.
        mInventoryAdapter = new InventoryAdapter(null, this);

        int numberOfColumns = 3;
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);

        // tells the layoutManager how many columns a specific item uses in its grid.
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mInventoryAdapter.getItemViewType(position);
            }
        });

        mRvBookInventory.setLayoutManager(layoutManager);

        mRvBookInventory.setAdapter(mInventoryAdapter);

        new BookFetcherAsyncTask(mInventoryAdapter).execute();
    }

    /**
     * fetches all information from the API which will be displayed.
     * The adapter was passed as a parameter so that this asyncTask can be static.
     */
    static class BookFetcherAsyncTask extends AsyncTask<Void, Void, List<Book>> {

        private InventoryAdapter adapter;

        public BookFetcherAsyncTask(InventoryAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected List<Book> doInBackground(Void... voids) {
            return BookInventoryUtility.getWebNovelsFromAPI();
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);
            if (adapter != null) {
                adapter.setBooks(books);
            }
        }
    }
}
