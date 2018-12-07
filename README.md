# HabitTrackerAppUdacity
HabitTrackerAppUdacity is an app in which you create a database which you create and can delete it.

### Features

* There exists a contract class that defines name of table and constants.
* Inside the contract class, there is an inner class for each table created.
* There exists a subclass of SQLiteOpenHelper that overrides onCreate() and onUpgrade().
* There is a single insert method that adds at least two values of two different datatypes (e.g. INTEGER, STRING) into the database using a ContentValues object and the insert() method.
* There is a single read method that returns a Cursor object. It should get the data repository in read and use the query() method to retrieve at least one column of data.
* No external libraries (e.g. Realm) are used for the database code, and no Content Providers is used. All data insertion and reading should be done using direct method calls to the SQLite database in the SQLiteOpenHelper class.
