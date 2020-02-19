# Program Organization
![alt text](/artifacts/Schedulo%20Architecture%20Context%20Diagram.png)
We need a description here

# Major Classes
It should identify
the responsibilities of each major class and how the class will interact with
other classes. It should include descriptions of the class hierarchies, of state
transitions, and of object persistence. If the system is large enough, it should
describe how classes are organized into subsystems.

# Data Design
should describe the major files and table designs to be used.
It should describe alternatives that were considered and justify the choices
that were made.

# Business Rules
Schedulo is not bound by any rules that impact the system's design.

# User Interface Design

![alt text](/artifacts/User%20Interface.png)

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
The architecture should provide estimates and explain why the architects
believe the performance goals are achievable. 

# Scalability
We have no plans to expand the size of this project after its completion for this class.

# Interoperability
If the system is expected to share data or resources with other software or
hardware, the architecture should describe how that will be accomplished.

# Internationalization/Localization

Since scheduling is a global need, Internationalization isn't a problem. Adding support for other languages is doable.

# Input/Output
* Input
	* Log in information
	* Event dates and times

* Output
	* Calendar with event information

# Error Processing
Error handling is often treated as a coding-convention-level issue, if it's
treated at all. But because it has systemwide implications, it is best treated at
the architectural level.
Examples in Code Complete excerpt Dr. H posted

# Fault Tolerance
Fault tolerance is a collection of techniques that increase a system's reliability
by detecting errors, recovering from them if possible, and containing their bad
effects if not.
Examples in Code Complete excerpt Dr. H posted

# Architectural Feasibility
Feasibility can be tested through the virtual Android device that Android Studio provides for testing.

# Overengineering
Pretty much how we avoid overengineering

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
