package info.camposha.mr_simplecursoradapter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class CustomDBHelper(context: Context?) : SQLiteOpenHelper(context, DB, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        try {
            db.execSQL(CREATE_TABLE) //
        } catch (ex: Exception) {
            Log.e("SimpleCursorAdapter_", ex.toString())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    companion object {
        private const val DB = "custom.db"
        private const val DB_VERSION = 1
        private const val CREATE_TABLE =
            "create table custom ( _id integer primary key, name string, age string);"
        private const val DROP_TABLE = "drop table custom;"
    }
}