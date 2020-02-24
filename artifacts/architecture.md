# Program Organization
![alt text](/artifacts/Schedulo%20Architecture%20Context%20Diagram.png)
The user interacts directly with the app by inputting their log in information and, once logged in, their event information.
The app interacts with the server by accessing the database to store events and check user information.

# Major Classes
![alt text](/artifacts/Schedulo%20__%20Class%20Diagram.png)
The Main Activity allows for a user to either register themselves as a user or login to their user account.
The User account holds the user's name, number, password, and calendar.
A manager is a user that is able to send events to other users.
The calendar is able to add, delete, or edit events created by users.
Events contain a title, start and end time, and location for users to view.

# Data Design
![alt text](/artifacts/Schedulo%20Architecture%20Server%20Component%20Diagram.jpg)
The server that the Schedulo app interacts with contains an Account Manager (to register, login, and authenticate users), Relationship Manager (allows managers to assign events to subordinates), Event Manager (for viewing, creating, editing, and deleting events), and a Database (to read and write all account information used by the server).

# Business Rules
Schedulo is not bound by any rules that impact the system's design.

# User Interface Design

![alt text](/artifacts/UI%20Diagram.png)

When the user opens the app, they will see a screen asking for their log in information, as well as a button to log in once the information is entered and a button to register if they do not have an account yet.
On the register screen, the user is asked for their name, phone number, and password. The phone number and password will be their log in information.
Once logged in, the user will be able to see their monthly calendar, along with buttons to add an event or log out. Clicking a day on the calendar will take them to the hourly view for that day, clicking the add event button will take them to the event add screen, and the log out button will take them back to the log in screen.
On the hourly view, the user will see the hours of the day on the left, with the events they have scheduled in blocks beside the time they are scheduled for.
On the event add screen, the user will be able to input the start time of their event, followed by the end time, and finally a check if it's all day.

# Resource Management
The architecture should describe a plan for managing scarce resources such as
database connections, threads, and handles. Memory management is another
important area for the architecture to treat in memory-constrained
applications areas such as driver development and embedded systems.

# Security
* Server is HTTPS enabled and passwords are set up in plaintext
* Passwords are hashed with bcrypt in the database

# Performance
Server preformance is prioritized by mostly just leaning on the preformance of Javalin and its dependencies. In conjunction with this the sql database schema is being designed with efficiency in mind.

# Scalability
We have no plans to expand the size of this project after its completion for this class.

# Interoperability
The system is not expected to share data or resources with any software or hardware outside of the app itself and its server.

# Internationalization/Localization
Since scheduling is a global need, Internationalization isn't a problem. Adding support for other languages is doable.

# Input/Output
* Input
	* Log in information
	* Event dates and times

* Output
	* Calendar with event information

# Error Processing
All server faults are thrown as exceptions and bubbled up to the user and returned as an appropriate response, no exception will ever cause the server to halt execution, and all errors encountered are written to the console despite not halting execution.

# Fault Tolerance
Every input from the database and users operates without assumptions and with the capabilites to deal with any possible recieved value even if they are unlikely and technically not possible.

# Architectural Feasibility
Feasibility can be tested through the virtual Android device that Android Studio provides for testing.

# Overengineering
Overengineering is avoided by not abstracting implementation beyond the needed capabilities, and only providing functionality strictly for what is needed by the user.

# Build-vs-Buy Decisions
## Client
* GSON
    * For serializing login info to JSON format and communicating with the server
* Volley
## Server
* Javalin
   * Http server
* Sqllite-jdbc
   * Mysql database wrapper
* Jbcrypt
   * Hashes passwords in the db

# Reuse
All preexisting software that is used is listed in the Build-vs-Buy section. Everything else is being made by the team.

# Change Strategy
Changes are handled by using GitHub to track any and all changes that occur during the weekly development.
