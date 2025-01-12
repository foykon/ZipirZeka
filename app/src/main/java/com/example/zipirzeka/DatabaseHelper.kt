package com.example.zipirzeka

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 2 // Versiyonu güncelle
        private const val TABLE_NAME = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        // Fotoğraflar için tablo
        private const val TABLE_USER_PHOTOS = "user_photos"
        private const val COLUMN_PHOTO_ID = "id"
        private const val COLUMN_PHOTO_PATH = "photo_path"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Kullanıcılar tablosu
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT,
                $COLUMN_PASSWORD TEXT
            );
        """
        db?.execSQL(createTableQuery)

        // Fotoğraflar tablosu
        val createUserPhotosTable = """
            CREATE TABLE $TABLE_USER_PHOTOS (
                $COLUMN_PHOTO_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_PHOTO_PATH TEXT
            );
        """
        db?.execSQL(createUserPhotosTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Tabloları güncelleme
        val dropUserTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        val dropUserPhotosTableQuery = "DROP TABLE IF EXISTS $TABLE_USER_PHOTOS"
        db?.execSQL(dropUserTableQuery)
        db?.execSQL(dropUserPhotosTableQuery)
        onCreate(db)
    }

    // Kullanıcı ekleme
    fun insertUser(username: String, password: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    // Kullanıcıyı kontrol etme
    fun readUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    // Fotoğraf ekleme
    fun insertUserPhoto(photoPath: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_PHOTO_PATH, photoPath)
        }
        val db = writableDatabase
        return db.insert(TABLE_USER_PHOTOS, null, values)
    }

    // Fotoğrafları alma
    fun getAllPhotos(): List<String> {
        val photos = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.query(TABLE_USER_PHOTOS, arrayOf(COLUMN_PHOTO_PATH), null, null, null, null, null)

        // Column index kontrolü
        val photoPathColumnIndex = cursor.getColumnIndex(COLUMN_PHOTO_PATH)

        if (photoPathColumnIndex != -1) { // Eğer sütun bulunursa
            while (cursor.moveToNext()) {
                val photoPath = cursor.getString(photoPathColumnIndex)
                photos.add(photoPath)
            }
        } else {
            Log.e("DatabaseHelper", "Column '$COLUMN_PHOTO_PATH' not found.")
        }

        cursor.close()
        return photos
    }

}
