
JavaFX App : Gracker
Creator : Mash Ibtesum

Current Status :
* Everything Works, there are some convenience features that aren't implemented, but doesn't hinder functionality. Those are marked by TODO comment tags in the code
* I have used the Jackson library to serialize the data to be saved. The required .jar files are included in the folder called libraries
* The app uses /Library/Application Support/ directory to store app data. It is hardcoded for Mac as of this readme as I do not have access or time to implement windows or linux
* Some tableview Controller specific stuff resides in view classes because they don't work properly in the controller class when moved

Gracker, a combination of the words grade and tracker, is an app that tracks
grades of students. It allows user to add their classes and for each classes,
wieght of different types of assignments and their grades for those assignments.
In the home/dashboard screen, it'll show an summary view of all course grade,
overall term GPA, Avergae and Letter Grade. A second iteration of the app might
also allow for cloud data saving, by utilizing a server side databse.

My application consists of multiple views, each showing differnet types of info
organized in different ways. Model view controller is important for specifically
my project because it allows me to seperate different views and compononent of the
project, allowing the views and project overall to easy to manage and modify, make the
codebase a lot more coheisve. While it is not applicable for my project, it would
also allow for other people to simaltaniously work on my project wihtout runnin$
conflicts or issues of that sort.

Core Classes :
Dashboard (VC) -  Displays the summary views
Course (MVC) - Detailed views of courses and the data model
Editor (VC) - The editor that pops out when the users adding a class. The user gets to input different types
        of assignments and their weights





