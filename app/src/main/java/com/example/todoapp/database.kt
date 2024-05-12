package com.example.todoapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class database(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {


    companion object {
        private const val DATABASE_NAME = "task.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Tasks"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAMe = "name"
        private const val COLUMN_PRIORITY = "priority"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_DATE = "date"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createquery =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY ,$COLUMN_NAMe VARCHAR(50),$COLUMN_PRIORITY VARCHAR(50),$COLUMN_DESCRIPTION VARCHAR(500),$COLUMN_DATE VARCHAR(30))"
        db?.execSQL(createquery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropquery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropquery)
        onCreate(db)
    }

    fun addtasks(task: task) {

        val db = writableDatabase
        val values = ContentValues().apply {

            put(COLUMN_NAMe, task.name)
            put(COLUMN_PRIORITY, task.priority)
            put(COLUMN_DESCRIPTION, task.description)
            put(COLUMN_DATE, task.date)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()


    }

    fun viewtasks(): List<task> {

        val tasklist = mutableListOf<task>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val courser = db.rawQuery(query, null)


        while (courser.moveToNext()) {
            val id = courser.getInt(courser.getColumnIndexOrThrow(COLUMN_ID))
            val name = courser.getString(courser.getColumnIndexOrThrow(COLUMN_NAMe))
            val pr = courser.getString(courser.getColumnIndexOrThrow(COLUMN_PRIORITY))
            val des = courser.getString(courser.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
            val date = courser.getString(courser.getColumnIndexOrThrow(COLUMN_DATE))
            val task = task(id, name, pr, des, date)

            tasklist.add(task)


        }
        courser.close()
        db.close()
        return tasklist
    }

    fun updatetask(task: task) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAMe, task.name)
            put(COLUMN_PRIORITY, task.priority)
            put(COLUMN_DESCRIPTION, task.description)
            put(COLUMN_DATE, task.date)


        }

        val where = "$COLUMN_ID=?"
        val wherea = arrayOf(task.id.toString())
        db.update(TABLE_NAME, values, where, wherea)
        db.close()


    }


    fun getbyid(taskid: Int): task {

        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=$taskid"
        val courser = db.rawQuery(query, null)
        courser.moveToNext()
        val id = courser.getInt(courser.getColumnIndexOrThrow(COLUMN_ID))
        val name = courser.getString(courser.getColumnIndexOrThrow(COLUMN_NAMe))
        val pr = courser.getString(courser.getColumnIndexOrThrow(COLUMN_PRIORITY))
        val des = courser.getString(courser.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
        val date = courser.getString(courser.getColumnIndexOrThrow(COLUMN_DATE))

        courser.close()
        db.close()
        return task(id, name, pr, des, date)


    }


    fun deletetask(taskid: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(taskid.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)


    }

}