# My notes and thoughts on the RecyclerView:

## ...for how the itemViews are recycled:

<img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/Lesson03/RecyclerView-smoothScroll.png?raw=true" alt="RecyclerView on smooth scrolling" height="300"/>

In T03.05-Going-Green the itemViews are colored and numerized to visualize the recycling.
If a smooth scroll is happening, then the items are beautifully recycled.
For example like this:  
  
Note: The not displayed items (start and end) are all in the cache (one list not two like pictured here).
  
{Not displayed Item 9}  
{Not displayed Item 10}  
====Begin Screen=======  
{Displayed Item 0}  
{Displayed Item 1}  
{Displayed Item 2}  
{Displayed Item 3}  
{Displayed Item 4}  
{Displayed Item 5}  
{Displayed Item 6}  
====End of the Screen====  
{Not displayed Item 7}  
{Not displayed Item 8}  
  
Next the 7 will be displayed, the 0 will be pushed() into the cache.
Then it looks like this:  
   
{Not displayed Item 10}  
{Not displayed Item 0}  
====Begin Screen=======  
{Displayed Item 1}  
{Displayed Item 2}  
{Displayed Item 3}  
{Displayed Item 4}  
{Displayed Item 5}  
{Displayed Item 6}  
{Displayed Item 7}  
====End of the Screen====  
{Not displayed Item 8}  
{Not displayed Item 9}  
  
But if you scroll like a maniac, then the recycling can't be done in time and something like this will be the result:

<img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/Lesson03/RecyclerView-maniacScroll.png?raw=true" alt="RecyclerView on maniac scrolling" height="300"/>

### But why is this so?
If I understood it correctly, then there are a specific amount of views in the cache.  
And if the list is scrolled really fast and it requests for more views than what are currently in the cache, then new ones will be created/inflated from the recycledPool.  
Meanwhile the views which got pushed out of the display are being recycled and put into the cache for later usage. And when there are too many views in the cache, then it will push those back into the recycledPool.
  
**State before fast scrolling:**  
  
====In the cache (recycled)====  
{Not displayed Item 7}  
{Not displayed Item 8}  
{Not displayed Item 9}  
{Not displayed Item 10}  
  
====Begin Screen=======  
{Displayed Item 0}  
{Displayed Item 1}  
{Displayed Item 2}  
{Displayed Item 3}  
{Displayed Item 4}  
{Displayed Item 5}  
{Displayed Item 6}  
====End of the Screen====  
  
**State after scrolling fast upwards (number desc.) before view calculation:**  
====In the cache (recycled)====  
{Not displayed Item 7}  
{Not displayed Item 8}  
{Not displayed Item 9}  
{Not displayed Item 10}  
  
====Begin Screen=======  
{Empty}  
{Empty}  
{Empty}  
{Empty}  
{Empty}  
{Displayed Item 0}  
{Displayed Item 1}  
====End of the Screen====  
  
====Items which are currently being recycled====  
{Not displayed Item 2}  
{Not displayed Item 3}  
{Not displayed Item 4}  
{Not displayed Item 5}  
{Not displayed Item 6}  
  
**And then it makes its calculations:**  
The ViewItems which have already been recycled (item 7&8) are inserted into the empty slots and then it looks like this:  
  
====In the cache (recycled)====  
-none  
  
====Begin Screen=======  
{Empty}  
{Displayed Item 7}  
{Displayed Item 8}  
{Displayed Item 9}  
{Displayed Item 10}  
{Displayed Item 0}  
{Displayed Item 1}  
====End of the Screen====  
  
====Items which are currently being recycled====  
{Not displayed Item 2}  
{Not displayed Item 3}  
{Not displayed Item 4}  
{Not displayed Item 5}  
{Not displayed Item 6}  
  
**But OhOh. All views from the cache were used but there is still one empty.**  
**A new view from the pool will be created/infalted and used instead of waiting for a view to be recycled.**  
  
====In the cache (recycled)====  
-none  
  
====Begin Screen=======  
{Displayed Item 11} //because 11 comes after 10  
{Displayed Item 7}  
{Displayed Item 8}  
{Displayed Item 9}  
{Displayed Item 10}  
{Displayed Item 0}  
{Displayed Item 1}  
====End of the Screen====  
  
====Items which are currently being recycled====  
{Not displayed Item 2}  
{Not displayed Item 3}  
{Not displayed Item 4}  
{Not displayed Item 5}  
{Not displayed Item 6}  
  
**In the meantime the views will be recycled and added back into the cache.**  
Then it looks like this:  
  
====In the cache (recycled)====  
{Not displayed Item 4}  
{Not displayed Item 5}  
{Not displayed Item 6}  
  
====Begin Screen=======  
{Displayed Item 11}  
{Displayed Item 7}  
{Displayed Item 8}  
{Displayed Item 9}  
{Displayed Item 10}  
{Displayed Item 0}  
{Displayed Item 1}  
====End of the Screen====  
  
====Items which are currently being recycled====  
{Not displayed Item 2}  
{Not displayed Item 3}  
  
**But for smooth scrolling there are not so many views needed (if scrolled slowly again).**  
And having too many views in the cache isn't good. So one view from the cache will be put into the pool for later usage.  
  
====In the cache (recycled)====  
{Not displayed Item 2}  
{Not displayed Item 3}  
{Not displayed Item 4}  
{Not displayed Item 5}  
-view06 moved into pool  
  
====Begin Screen=======  
{Displayed Item 11} //because 11 comes after 10  
{Displayed Item 7}  
{Displayed Item 8}  
{Displayed Item 9}  
{Displayed Item 10}  
{Displayed Item 0}  
{Displayed Item 1}  
====End of the Screen====  
  
====Items which are currently being recycled====  
-none  
  
**And if you start smooth scrolling now, then it will end up like the example below.**   
And if you change between fast and slow scrolling then it will end up even more messed up.  
  
====In the cache (recycled)====  
{Not displayed Item 2}  
{Not displayed Item 3}  
{Not displayed Item 4}  
  
====Begin Screen=======  
{Not displayed Item 5}  
{Displayed Item 11}  
{Displayed Item 7}  
{Displayed Item 8}  
{Displayed Item 9}  
{Displayed Item 10}  
{Displayed Item 0}  
====End of the Screen====  
  
====Items which are currently being recycled====  
{Displayed Item 1}  
  
### The RecyclerView-Lifecycle:  
This image is from an AndroidDeveloper-YouTube-Video: https://www.youtube.com/watch?v=LqBlYJTfLP4
  
<img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/Lesson03/androidIO-recyclerViewLifecycle.png?raw=true" alt="RecyclerView lifecycle" height="300"/>
