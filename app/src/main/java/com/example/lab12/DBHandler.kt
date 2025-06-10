package com.example.lab12

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_Users ("
                + "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$KEY_NAME TEXT,"
                + "$KEY_LOC TEXT,"
                + "$KEY_DESG TEXT)")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_Users")
        onCreate(db)
    }

    // Thêm người dùng mới
    fun insertUserDetails(name: String, location: String, designation: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_NAME, name)
            put(KEY_LOC, location)
            put(KEY_DESG, designation)
        }
        db.insert(TABLE_Users, null, values)
        db.close()
    }

    // Lấy tất cả người dùng
    fun getUsers(): ArrayList<HashMap<String, String>> {
        val db = this.readableDatabase
        val userList = ArrayList<HashMap<String, String>>()
        val query = "SELECT $KEY_NAME, $KEY_LOC, $KEY_DESG FROM $TABLE_Users"
        val cursor: Cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val user = HashMap<String, String>()
                user["name"] = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                user["designation"] = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESG))
                user["location"] = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LOC))
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return userList
    }

    // Lấy người dùng theo ID
    fun getUserByUserId(userid: Int): ArrayList<HashMap<String, String>> {
        val db = this.readableDatabase
        val userList = ArrayList<HashMap<String, String>>()
        val cursor = db.query(
            TABLE_Users,
            arrayOf(KEY_NAME, KEY_LOC, KEY_DESG),
            "$KEY_ID = ?",
            arrayOf(userid.toString()),
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val user = HashMap<String, String>()
            user["name"] = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
            user["designation"] = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESG))
            user["location"] = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LOC))
            userList.add(user)
        }
        cursor.close()
        return userList
    }

    // Xoá người dùng theo ID
    fun deleteUser(userid: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_Users, "$KEY_ID = ?", arrayOf(userid.toString()))
        db.close()
    }

    // Cập nhật người dùng theo ID
    fun updateUserDetails(location: String, designation: String, id: Int): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_LOC, location)
            put(KEY_DESG, designation)
        }
        return db.update(TABLE_Users, values, "$KEY_ID = ?", arrayOf(id.toString()))
    }

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "usersdb"
        private const val TABLE_Users = "userdetails"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_LOC = "location"
        private const val KEY_DESG = "designation"
    }
}
