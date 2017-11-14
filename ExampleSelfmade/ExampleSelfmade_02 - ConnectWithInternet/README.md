# Connect to the internet!

The app looks rather dull with dummy data.  
In this project, data from an API will be read and used to display as books.  

Before      |  After
:-------------------------:|:-------------------------:
<img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/ExamplesSelfmade/StartView.png?raw=true" alt="RecyclerView (Grid) on Start" height="300"/>  |  <img src="https://github.com/BeatingAngel/UdacityAndroidChallenge/blob/master/Notes-images/ExamplesSelfmade/newUI-View.png?raw=true" alt="RecyclerView (Grid) show grid" height="300"/>
  
## What is interesting in this project?  

**Topics like:**  
I can't display the book-cover in the main thread because it uses the network. So the idea of moving that task into a new asyncTask comes to mind. But no! The imageView can only be edited by its creator. So how can we display it?  
Solution: method called runOnUiThread()  
  
How do I make the text semi transparent over the book?  
Solution: change the alpha value.  
Example: normal color code for black (#000000) and one for 80% transparence (#CC000000).

AsyncTasks should be static in order to prevent leaks. But how do I do this?  
Pass every variable the task uses as a variable to it.  
But what if the variable have different types? I can't put different types here: AsyncTask<Parameters, Progress, Result>  
Solution: create a new class which stores all thore variables and pass the helperClass as a parameter.  
A example is in the Adapter-class of this project.  
  
## Note:  
I didn't use an API for the book-data because I haven't found a suitable one which I can use without copyright issues. (I can't use everyone, right? Right??)
