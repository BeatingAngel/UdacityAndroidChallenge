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
  
### The RecyclerView-Lifecycle:  
This image is from an AndroidDeveloper-YouTube-Video: https://www.youtube.com/watch?v=LqBlYJTfLP4
  
<img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/Lesson03/androidIO-recyclerViewLifecycle.png?raw=true" alt="RecyclerView lifecycle" height="300"/>
