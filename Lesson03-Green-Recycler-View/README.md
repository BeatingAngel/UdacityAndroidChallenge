# My notes and thoughts on the RecyclerView:

## ...for how the itemViews are recycled:
In T03.05-Going-Green the itemViews are colored and numerized to visualize the recycling.
If a smooth scroll is happening, then the items are beautifully recycled.
For example like this:
  
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
  
Next the 7 will be displayed, the 0 will be pushed() into the startQueue and the 9 will be appended() to the endQueue.
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
