package com.example.android.habittrackerapp.data;
/**
 * This project is done by Khaidem Sandip Singha under the Udacity Android Foundations Nanodegree program.
 *
 * I confirm that this submission is my own work. I have not used code from any other Udacity student's or graduate's submission of the same project.
 * I understand that Udacity will check my submission for plagiarism, and that failure to adhere to the Udacity Honor Code may result in the cancellation of my
 * enrollment.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PuzzleDbHelper extends SQLiteOpenHelper {

    public  static final String LOG_TAG = PuzzleDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "puzzleWorld.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link PuzzleDbHelper}.
     *
     * @param context of the app
     */
    public PuzzleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PUZZLES_TABLE = " CREATE TABLE " + PuzzleContract.PuzzleEntry.TABLE_NAME + " (" +
                PuzzleContract.PuzzleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PuzzleContract.PuzzleEntry.COLUMN_PUZZLE_NAME + " TEXT NOT NULL, " +
                PuzzleContract.PuzzleEntry.COLUMN_PUZZLE_AUTHOR + " TEXT, " +
                PuzzleContract.PuzzleEntry.COLUMN_AUTHOR_GENDER + " INTEGER NOT NULL," +
                PuzzleContract.PuzzleEntry.COLUMN_PUZZLE_DURATION + " INTEGER NOT NULL DEFAULT 0);";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PUZZLES_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
