# Schedulo - The Schedule Management Tool For Android

## Vision Statement
Schedulo is for anyone who is busy and need to organize their schedules easily and quickly. Our product is suitable for the general public, along with buisness owners/managers and is meant for anybody to be able to use, for any scheduling need. Schedulo is an app that allows users to upload and invite fellow users to events on a private message board and calendar. The application supplies availability information to those who are interested, and allows for people to assign events such as a manager assigning shifts at a workplace, or propose events in the case of friends just trying to find a day to hang out. That leads to faster coordination and event planning for groups with busy schedules. Once the compilation of schedules has been gathered, the event coordinator can easily identify and select a suitable meeting time for the entire group. Unlike the other paid scheduling apps, like HotSchedules, our product is free and offers generic utility outside of the workplace, by allowing users to schedule time with friends or for appointments, or in the workplace, by allowing users to check when they're scheduled or to switch shifts with thier coworkers.

## Group Members
* Samantha Del Rosario
* Grant Mullinax
* Andy Phan
* Andres Velazquez

## Sprint 1
* [Product Backlog / Sprint Backlog](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* [Requirements](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/requirements.md)
* [Trello Board](https://trello.com/b/9H7LNIEr/schedule-project)
* [Burndown Chart / Velocity Chart](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)

## Sprint 2
* [Product Backlog / Sprint Backlog](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* [Requirements](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/requirements.md)
* [Trello Board](https://trello.com/b/9H7LNIEr/schedule-project)
* [Burndown Chart / Velocity Chart](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* Design Documents
    * [Context Diagram](https://docs.google.com/drawings/d/1x0TNeWScxqYFPz7hsrK8bibvXTgXxpFZGY4P3sLvfkc)
    * [Container Diagram](https://docs.google.com/drawings/d/1O8AHw3tQyJEcRltbBX12G2N6gbYTFzO9zbenqoTwZh8)
    * [Server Component Diagram](https://docs.google.com/drawings/d/1B-oxczOIYy5Rx0QgYyja2nvQJq-PtxW0XzPFU0ym2aA)
    * [Server Code Diagram](https://docs.google.com/drawings/d/1lagTMMsODtt2wjhp_Prh2UYJPCuoy_UAmadyvqGxrVU)
    * [App Component Diagram](https://drive.google.com/file/d/1DO-DUoxMLjF8pbk0AvU4kWtS_g_s_NIv/view?usp=sharing)
* [Demonstration](https://www.youtube.com/watch?v=OU8_D61vIxg&feature=youtu.be)

## Sprint 3
* [Product Backlog / Sprint Backlog](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* [Requirements](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/requirements.md)
* [Trello Board](https://trello.com/b/9H7LNIEr/schedule-project)
* [Burndown Chart / Velocity Chart](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* Design Documents
    * [Architecture](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/architecture.md)
    * [Class Diagram](https://docs.google.com/drawings/d/1OK6jF0HXs6KCT5-PJrCwCR5yFjp-USOpkegakGwRKKU/edit?usp=sharing)
    * [Context Diagram](https://docs.google.com/drawings/d/1x0TNeWScxqYFPz7hsrK8bibvXTgXxpFZGY4P3sLvfkc)
    * [Container Diagram](https://docs.google.com/drawings/d/1O8AHw3tQyJEcRltbBX12G2N6gbYTFzO9zbenqoTwZh8)
    * [Server Component Diagram](https://docs.google.com/drawings/d/1B-oxczOIYy5Rx0QgYyja2nvQJq-PtxW0XzPFU0ym2aA)
    * [Server Code Diagram](https://docs.google.com/drawings/d/1lagTMMsODtt2wjhp_Prh2UYJPCuoy_UAmadyvqGxrVU)
    * [App Component Diagram](https://drive.google.com/file/d/1DO-DUoxMLjF8pbk0AvU4kWtS_g_s_NIv/view?usp=sharing)
* Source Code
    * [App](https://github.com/grant-mullinax/schedulo/tree/master/Schedulo/app/src/main/java/com/example/schedulo)
    * [Server](https://github.com/grant-mullinax/schedulo/tree/master/schedulo-server)
* Automated Tests
    * [Login](https://github.com/grant-mullinax/schedulo/tree/master/Schedulo/app/src/androidTest/java/com/example/schedulo)
    * [Calendar Event](https://github.com/grant-mullinax/schedulo/blob/master/Schedulo/app/src/test/java/com/example/schedulo/CalendarEventTest.java)
    * [Server Actions](https://github.com/grant-mullinax/schedulo/blob/master/schedulo-server/src/test/kotlin/UserManagerTestSuite.kt)
* [Demonstration](https://www.youtube.com/watch?v=iVLPHW1K6Hg)

## Sprint 4
### Responsibilities
* Sam
    * Create UI for adding events to calendar
    * Continue debugging requests from app to server for log in and register functionality
    * Record app demonstration
* Grant 
    * Added database schema for individual users.
    * Implemented endpoints for adding and viewing events.
    * Added tests for event actions.
* Andy
    * Added logout feature.
    * Added tests for login and calendar events.
    * Updated network config from domain to base.
* Andres
    * Create calendar event object
    * Transfer product backlog to Trello
    * Added architecture document template along with various descriptions

* [Sprint Backlog](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* [Requirements](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/requirements.md)
* [Trello Board](https://trello.com/b/9H7LNIEr/schedule-project)
* [Burndown Chart / Velocity Chart](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* Design Documents
    * [Architecture](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/architecture.md)
    * [Server Code Diagram](https://docs.google.com/drawings/d/1lagTMMsODtt2wjhp_Prh2UYJPCuoy_UAmadyvqGxrVU)
    * [App Component Diagram](https://drive.google.com/file/d/1DO-DUoxMLjF8pbk0AvU4kWtS_g_s_NIv/view?usp=sharing)
* Source Code
    * [App](https://github.com/grant-mullinax/schedulo/tree/master/Schedulo/app/src/main/java/com/example/schedulo)
    * [Server](https://github.com/grant-mullinax/schedulo/tree/master/schedulo-server)
* Automated Tests
    * [Login](https://github.com/grant-mullinax/schedulo/tree/master/Schedulo/app/src/androidTest/java/com/example/schedulo)
    * [Calendar Event](https://github.com/grant-mullinax/schedulo/blob/master/Schedulo/app/src/test/java/com/example/schedulo/CalendarEventTest.java)
    * [Server Actions](https://github.com/grant-mullinax/schedulo/blob/master/schedulo-server/src/test/kotlin/UserManagerTestSuite.kt)
* [Demonstration](https://www.youtube.com/watch?v=lw0A_fiKHTk&feature=youtu.be)

## Sprint 5
### Responsibilities
* Sam

* Grant 
   * Fixed requirements table format
   * Managed sprint backlog and planning
   * Updated event endpoint formatting to allow user-specific results

* Andy
    * Fix requirements table
    * Add view events feature
    * Update unit test for Calendar Event
    * Add view events test
 
* Andres
    * Read input from edit event screen
    * New 3rd party calendar for event displaying
    * Relating user stories to design documents

* [Sprint Backlog](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* [Requirements](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/requirements.md)
* [Trello Board](https://trello.com/b/9H7LNIEr/schedule-project)
* [Burndown Chart / Velocity Chart](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* Design Documents
    * [Architecture](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/architecture.md)
    * [Server Code Diagram](https://docs.google.com/drawings/d/1lagTMMsODtt2wjhp_Prh2UYJPCuoy_UAmadyvqGxrVU)
    * [App Component Diagram](https://drive.google.com/file/d/1DO-DUoxMLjF8pbk0AvU4kWtS_g_s_NIv/view?usp=sharing)
* Source Code
    * [App](https://github.com/grant-mullinax/schedulo/tree/master/Schedulo/app/src/main/java/com/example/schedulo)
    * [Server](https://github.com/grant-mullinax/schedulo/tree/master/schedulo-server)
* Automated Tests
    * [Login](https://github.com/grant-mullinax/schedulo/tree/master/Schedulo/app/src/androidTest/java/com/example/schedulo)
    * [Calendar Event](https://github.com/grant-mullinax/schedulo/blob/master/Schedulo/app/src/test/java/com/example/schedulo/CalendarEventTest.java)
    * [Server Actions](https://github.com/grant-mullinax/schedulo/blob/master/schedulo-server/src/test/kotlin/UserManagerTestSuite.kt)
    * [View Events](https://github.com/grant-mullinax/schedulo/blob/master/Schedulo/app/src/androidTest/java/com/example/schedulo/ViewEventTest.java)
* [Demonstration](https://youtu.be/_A_89TQpWeQ)

## Sprint 6
### Responsibilities
* Sam
   * Implement event sharing
   * Clean up UI

* Grant 


* Andy
   * Added group by date feature for view events
   * Made events in view events clickable
   * Added click event test

 
* Andres


* [Sprint Backlog](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* [Requirements](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/requirements.md)
* [Trello Board](https://trello.com/b/9H7LNIEr/schedule-project)
* [Burndown Chart / Velocity Chart](https://docs.google.com/spreadsheets/d/1WA1FlVTJni7GUQFl0b-y6quCHUnMSNcDjcW2Qbvj9tE/)
* Design Documents
    * [Architecture](https://github.com/grant-mullinax/schedulo/blob/master/artifacts/architecture.md)
    * [Server Code Diagram](https://docs.google.com/drawings/d/1lagTMMsODtt2wjhp_Prh2UYJPCuoy_UAmadyvqGxrVU)
    * [App Component Diagram](https://drive.google.com/file/d/1DO-DUoxMLjF8pbk0AvU4kWtS_g_s_NIv/view?usp=sharing)
* Source Code
    * [App](https://github.com/grant-mullinax/schedulo/tree/master/Schedulo/app/src/main/java/com/example/schedulo)
    * [Server](https://github.com/grant-mullinax/schedulo/tree/master/schedulo-server)
* Automated Tests
    * [Login](https://github.com/grant-mullinax/schedulo/tree/master/Schedulo/app/src/androidTest/java/com/example/schedulo)
    * [Calendar Event](https://github.com/grant-mullinax/schedulo/blob/master/Schedulo/app/src/test/java/com/example/schedulo/CalendarEventTest.java)
    * [Server Actions](https://github.com/grant-mullinax/schedulo/blob/master/schedulo-server/src/test/kotlin/UserManagerTestSuite.kt)
    * [View Events](https://github.com/grant-mullinax/schedulo/blob/master/Schedulo/app/src/androidTest/java/com/example/schedulo/ViewEventTest.java)
* [Demonstration]
