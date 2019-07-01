package com.example.sqliteroomsample.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sqliteroomsample.data.model.Employee
import java.lang.Exception

class DatabaseHelper private constructor(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /*Create table*/
    override fun onCreate(db: SQLiteDatabase?) {

        Log.d("TAG", "onCreate DB")

        db?.execSQL(SQL_CREATE_DB)
    }

    /*Called when db upgraded: modify column*/
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("TAG", "onUpgrade DB")

        db?.run {
            execSQL(SQL_UPGRADE_DB)
            onCreate(db)
        }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertEmployee(employee: Employee): Long {
        var newRow: Long = -1
        val db = writableDatabase
        db?.beginTransaction()
        try {
            val values = ContentValues().apply {
                put(COLUMN_ADDRESS, employee.address)
                put(COLUMN_PHONE_NUMBER, employee.phone)
            }

            newRow = db?.insert(TABLE_NAME, "name", values)!!
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db?.endTransaction()
        }

        return newRow
    }

    companion object {
        private const val DATABASE_NAME = "sun.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "employee"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_ADDRESS = "address"
        private const val COLUMN_PHONE_NUMBER = "phone_number"

        private const val SQL_CREATE_DB = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_ADDRESS TEXT," +
                "$COLUMN_PHONE_NUMBER TEXT )"

        private const val SQL_UPGRADE_DB = "DROP TABLE IF EXISTS $TABLE_NAME"

        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper = instance ?: synchronized(this) {
            instance ?: DatabaseHelper(context)
        }
    }
}
