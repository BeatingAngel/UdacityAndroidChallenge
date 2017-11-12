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

**But how do I change between different layout.xml-Files for every item in my list?**  
When an Adapter is created, then the onCreateViewHolder-Method will have a second parameter called viewType.  
This int-value is returned by a function which can also be overridden. It's called getItemViewType(int position).  
In this method something like this can be done: 
     
     Book book = bookList.get(position);
     if (book.getColSize() == 1) {
         //it is a book.
         return 1;
     } else {
         //it is a genre-title!
         return 3; //for a column-span of 3
     }

So in our onCreateViewHolder-Method we now have the colspan for the item which will be created.  
So in our code (see below) we can check if the viewType is 1 for a book or 3 for a genre-title and inflate a layout based on that.  
**BUT HOLD ON!**  
Every layout has different views/ids in it and therefore different items to overwrite and get access to in the ViewHolder-class.  
So how do we know in our ViewHolder-class which layout it is?  
That's easy to tell if we set a tag on out view with view.setTag(layoutIdForBookType) and get that tag later in our ViewHolder-class.

    Context context = parent.getContext();
    int layoutIdForBookType =
           viewType == BookType.BOOK_ITEM.getItemSize()
                        ? R.layout.inventory_list_book_item
                        : R.layout.inventory_list_book_genre;

    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(layoutIdForBookType, parent, false);

    view.setTag(layoutIdForBookType);

    return new BookViewHolder(view);

In the viewHolder-class constructor we can check the tag and get the textView which is there like this:  

    layoutId = (int)itemView.getTag();

    if (layoutId == R.layout.inventory_list_book_item) {
       itemText = itemView.findViewById(R.id.tv_bookTitle);
    } else {
       itemText = itemView.findViewById(R.id.tv_genreTitle);
    }
