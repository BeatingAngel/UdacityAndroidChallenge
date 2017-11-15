package com.goldencrow.android.blackraven;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldencrow.android.blackraven.entities.InventoryItem;
import com.goldencrow.android.blackraven.entities.enums.InventoryItemType;

import java.util.List;

/**
 * @author Philipp Hermüller
 * @version 14.11.2017
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ItemViewHolder> {

    private static final String TAG = InventoryAdapter.class.getSimpleName();

    private InventoryAdapterOnClickHandler mInventoryClickHandler;
    private List<InventoryItem> mItems;

    public interface InventoryAdapterOnClickHandler {
        void ItemOnClick(InventoryItem item);
    }

    public InventoryAdapter(InventoryAdapterOnClickHandler handler, List<InventoryItem> items) {
        this.mInventoryClickHandler = handler;
        this.mItems = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForItem =
                viewType == InventoryItemType.SINGLE_ITEM.getItemSize()
                        ? R.layout.inventory_single_item
                        : R.layout.inventory_genre_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate the matching layout for the view.
        View view = inflater.inflate(layoutIdForItem, parent, false);

        // remember the layout for the view.
        view.setTag(layoutIdForItem);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        // get current item
        InventoryItem item = mItems.get(position);

        // display its name
        holder.mItemText.setText(item.getName());

        holder.item = item;

        // ... and draw the matching image if it is a singleItem.
        if (item.getType() == InventoryItemType.SINGLE_ITEM) {
            if (!item.getName().toLowerCase().equals("none")) {
                holder.mItemImage.setImageResource(R.drawable.health_potion);
            } else {
                holder.mItemImage.setImageResource(R.drawable.ic_launcher_background);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType().getItemSize();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // displays the name of the item/genre
        TextView mItemText;

        // displays an image of the item if possible
        ImageView mItemImage;

        // tells if this view is a genreItem or a singleItem.
        final int LAYOUT_ID;

        InventoryItem item;

        public ItemViewHolder(View itemView) {
            super(itemView);

            LAYOUT_ID = (int)itemView.getTag();

            // get the appropriate viewItems from the layout.
            if (LAYOUT_ID == R.layout.inventory_single_item) {
                mItemText = itemView.findViewById(R.id.tv_itemName);
                mItemImage = itemView.findViewById(R.id.iv_itemImage);
            } else {
                mItemText = itemView.findViewById(R.id.tv_genreTitle);
            }

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mInventoryClickHandler.ItemOnClick(item);
        }
    }
}































