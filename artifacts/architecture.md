# Program Organization
Can be found in the readme

# Major Classes
Can be found in the readme

# Data Design
Can be found in the readme

# Business Rules

# User Interface Design

![alt text](/artifacts/User%20Interface.png)

When the user opens the app, they will see a screen asking for their log in information, as well as a button to log in once the information is entered and a button to register if they do not have an account yet.
On the register screen, the user is asked for their name, phone number, and password. The phone number and password will be their log in information.
Once logged in, the user will be able to see their monthly calendar, along with buttons to add an event or log out. Clicking a day on the calendar will take them to the hourly view for that day, clicking the add event button will take them to the event add screen, and the log out button will take them back to the log in screen.
On the hourly view, the user will see the hours of the day on the left, with the events they have scheduled in blocks beside the time they are scheduled for.
On the event add screen, the user will be able to input the start time of their event, followed by the end time, and finally a check if it's all day.

# Resource Management

# Security
* Server is HTTPS enabled and passwords are set up in plaintext
* Passwords are hashed with bcrypt in the database

# Performance

# Scalability

# Interoperability

# Internationalization/Localization

# Input/Output

# Error Processing

# Fault Tolerance

# Architectural Feasibility

# Overengineering

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

# Change Strategy
