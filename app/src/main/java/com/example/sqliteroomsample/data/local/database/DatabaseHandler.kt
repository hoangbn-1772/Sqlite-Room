package com.example.sqliteroomsample.data.local.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.room.Transaction
import com.example.sqliteroomsample.data.model.sqlite.Employee

class DatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

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
                put(COLUMN_NAME, employee.name)
                put(COLUMN_ADDRESS, employee.address)
                put(COLUMN_PHONE_NUMBER, employee.phone)
            }

            newRow = db?.insert(TABLE_NAME, "name", values)!!
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db?.endTransaction()
            db?.close()
        }

        return newRow
    }

    fun getAllEmployee(): List<Employee>? {
        val employees = mutableListOf<Employee>()
        val db = readableDatabase
        try {
            val projection = arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_ADDRESS, COLUMN_PHONE_NUMBER)
            val cursor = db.query(TABLE_NAME, projection, null, null, null, null, null)
            with(cursor) {
                while (moveToNext()) {
                    employees.add(
                        Employee(
                            getInt(getColumnIndexOrThrow(COLUMN_ID)),
                            getString(getColumnIndexOrThrow(COLUMN_NAME)),
                            getString(getColumnIndexOrThrow(COLUMN_ADDRESS)),
                            getString(getColumnIndexOrThrow(COLUMN_PHONE_NUMBER))
                        )
                    )
                }
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db?.close()
        }
        return employees
    }

    fun getEmployeeNameById(employeeId: Int): String? {
        var employeeName: String? = null
        val db = readableDatabase

        try {
            /*Cac truong se lay ra tu CSDL*/
            val projection = arrayOf(COLUMN_NAME, COLUMN_ADDRESS)

            /*Filter result WHERE "id" = 'employeeId'*/
            val selection = "$COLUMN_ID = ?"
            val selectionArgs = arrayOf("$employeeId")

            val cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            with(cursor) {
                while (moveToNext()) {
                    employeeName = getString(getColumnIndexOrThrow(COLUMN_NAME))
                }
                close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db?.close()
        }

        return employeeName
    }

    fun updateEmployee(employee: Employee): Int {
        var count = 0
        val db = writableDatabase
        db?.beginTransaction()

        try {
            // New value for one column
            val values = ContentValues().apply {
                put(COLUMN_ADDRESS, employee.address)
            }

            val selection = "$COLUMN_ADDRESS = ?"
            val selectionArgs = arrayOf("HN")

            count = db.update(TABLE_NAME, values, selection, selectionArgs)
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db?.endTransaction()
            db?.close()
        }

        return count
    }

    fun deleteEmployee(employee: Employee): Int {
        var count = 0
        val db = writableDatabase
        db?.beginTransaction()
        try {
            val selection = "$COLUMN_NAME = ?"
            val selectionArgs = arrayOf(employee.name)

            count = db.delete(TABLE_NAME, selection, selectionArgs)
            db?.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db?.endTransaction()
            db?.close()
        }
        return count
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

        fun getInstance(context: Context): DatabaseHelper = instance
            ?: synchronized(this) {
                instance
                    ?: DatabaseHelper(context)
            }
    }
}
