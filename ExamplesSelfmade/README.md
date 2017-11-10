# Book Inventory
The aim of this project is to explore the RecyclerView with a GridLayoutManager and display different items (layouts) with different sizes in a list.

## Result:
GridLayout Scroll      |  GridLayout (show grid)
:-------------------------:|:-------------------------:
<img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/ExamplesSelfmade/StartView.png?raw=true" alt="RecyclerView (Grid) on Start" height="300"/>  |  <img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/ExamplesSelfmade/StartViewLayout.png?raw=true" alt="RecyclerView (Grid) show grid" height="300"/>
<img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/ExamplesSelfmade/AfterScrollView.png?raw=true" alt="RecyclerView (Grid) after Scroll" height="300"/> | 

In order that an element can span over multiple columns, a SpanSizeLookUp is needed.

    int numberOfColumns = 3;
    GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);

    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            return mInventoryAdapter.getItemViewType(position);
        }
     });

     mGameInventory.setLayoutManager(layoutManager);

The getSpanSize-Method requests the columnSpanSize with a position.  
For example how broad is the 9th element in the dataList?

A further explanation will follow shortly later . . .
