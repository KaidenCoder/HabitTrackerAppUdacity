package com.example.android.habittrackerapp.data;
/**
 * This project is done by Khaidem Sandip Singha under the Udacity Android Foundations Nanodegree program.
 *
 * I confirm that this submission is my own work. I have not used code from any other Udacity student's or graduate's submission of the same project.
 * I understand that Udacity will check my submission for plagiarism, and that failure to adhere to the Udacity Honor Code may result in the cancellation of my
 * enrollment.
 */
import android.provider.BaseColumns;

public class PuzzleContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PuzzleContract(){}

    /**
     * Inner class that defines constant values for the puzzle database table.
     * Each entry in the table represents a single puzzle.
     */
    public static final class PuzzleEntry implements BaseColumns{

        /** Name of database table for puzzles */
        public final static String TABLE_NAME = "puzzles";

        /**
         * Unique ID number for the puzzle (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the puzzle.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PUZZLE_NAME = "name";

        /**
         * Author of the puzzle.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PUZZLE_AUTHOR = "author";

        /**
         * Gender of the author.
         *
         * The only possible values are {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
         * or {@link #GENDER_FEMALE}.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_AUTHOR_GENDER = "gender";
        /**
         * Possible values for the gender of the author.
         */
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        /**
         * Time taken to solve the puzzle
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PUZZLE_DURATION = "time";

    }
}