package com.example.android.habittrackerapp;
/**
 * This project is done by Khaidem Sandip Singha under the Udacity Android Foundations Nanodegree program.
 *
 * I confirm that this submission is my own work. I have not used code from any other Udacity student's or graduate's submission of the same project.
 * I understand that Udacity will check my submission for plagiarism, and that failure to adhere to the Udacity Honor Code may result in the cancellation of my
 * enrollment.
 */
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittrackerapp.data.PuzzleContract.PuzzleEntry;
import com.example.android.habittrackerapp.data.PuzzleDbHelper;

public class HabitActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private PuzzleDbHelper mDbHelper;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HabitActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new PuzzleDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        db = mDbHelper.getReadableDatabase();

        TextView displayView = findViewById(R.id.text_view_puzzle);

        Cursor cursor = readCursor();

        try {
            displayView.setText("The puzzle table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(PuzzleEntry._ID + " - " +
                    PuzzleEntry.COLUMN_PUZZLE_NAME + " - " +
                    PuzzleEntry.COLUMN_PUZZLE_AUTHOR + " - " +
                    PuzzleEntry.COLUMN_AUTHOR_GENDER + " - " +
                    PuzzleEntry.COLUMN_PUZZLE_DURATION + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(PuzzleEntry._ID);
            int puzzleColumnIndex = cursor.getColumnIndex(PuzzleEntry.COLUMN_PUZZLE_NAME);
            int authorColumnIndex = cursor.getColumnIndex(PuzzleEntry.COLUMN_PUZZLE_AUTHOR);
            int genderColumnIndex = cursor.getColumnIndex(PuzzleEntry.COLUMN_AUTHOR_GENDER);
            int timeColumnIndex = cursor.getColumnIndex(PuzzleEntry.COLUMN_PUZZLE_DURATION);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentPuzzle = cursor.getString(puzzleColumnIndex);
                String currentAuthor = cursor.getString(authorColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentDuration = cursor.getInt(timeColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentPuzzle + " - " +
                        currentAuthor + " - " +
                        currentGender + " - " +
                        currentDuration));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    // Perform this raw SQL query "SELECT * FROM puzzle"
    // to get a Cursor that contains all rows from the pets table.
    public Cursor readCursor() {
        String[] projection = {
                PuzzleEntry._ID,
                PuzzleEntry.COLUMN_PUZZLE_NAME,
                PuzzleEntry.COLUMN_PUZZLE_AUTHOR,
                PuzzleEntry.COLUMN_AUTHOR_GENDER,
                PuzzleEntry.COLUMN_PUZZLE_DURATION
        };

        Cursor cursor = db.query(
                PuzzleEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    /**
     * Helper method to insert hardcoded puzzle data into the database. For debugging purposes only.
     */
    private void insertPuzzles(){
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Sudoku puzzle attributes are the values.
        ContentValues values = new ContentValues();
        values.put(PuzzleEntry.COLUMN_PUZZLE_NAME, "Sudoku");
        values.put(PuzzleEntry.COLUMN_PUZZLE_AUTHOR, "Thomas Snyder");
        values.put(PuzzleEntry.COLUMN_AUTHOR_GENDER, PuzzleEntry.GENDER_MALE);
        values.put(PuzzleEntry.COLUMN_PUZZLE_DURATION, 3);

        long newRowId = db.insert(PuzzleEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving puzzle", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Puzzle saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_habit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPuzzles();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

