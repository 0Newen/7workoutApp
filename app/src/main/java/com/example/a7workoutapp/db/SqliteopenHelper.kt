package com.example.a7workoutapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteopenHelper(context:Context, factory: SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(context, DATABASE_NAME,factory, DATABASE_VERSION) {

    companion object{
        private val DATABASE_VERSION=1
        private val DATABASE_NAME="SevenWorkout.db"
        private val TABLE_HISTORY="history"
        private val COLUMN_ID="_id"
        private val COLUM_COMPLETED_DATE="completed_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_HISTORY_TABLE = ("CREATE TABLE " +
                TABLE_HISTORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUM_COMPLETED_DATE
                + " TEXT)")
        db?.execSQL(CREATE_HISTORY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    fun addDate(date: String) {
        val values =
            ContentValues() // Creates an empty set of values using the default initial size
        values.put(
            COLUM_COMPLETED_DATE,
            date
        ) // Putting the value to the column along with the value.
        val db =
            this.writableDatabase
        db.insert(TABLE_HISTORY, null, values)
        db.close()
    }

    fun getAllCompletedDatesList(): ArrayList<String> {
        val list = ArrayList<String>() // ArrayList is initialized
        val db =
            this.readableDatabase // Create and/or open a database that will be used for reading and writing.
        //  Runs the provided SQL and returns a Cursor over the result set.
        // Query for selecting all the data from history table.
        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY", null)

        // Move the cursor to the next row.
        while (cursor.moveToNext()) {
            // Returns the zero-based index for the given column name, or -1 if the column doesn't exist.
            list.add(cursor.getString(cursor.getColumnIndex(COLUM_COMPLETED_DATE))) // value is added in the list
        }
        cursor.close() // Cursor is closed after its used.
        return list // List is returned.
    }
}