package info.camposha.mr_simplecursoradapter

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mHlpr: CustomDBHelper? = null
    var mSqlite: SQLiteDatabase? = null
    var mAdapter: SimpleCursorAdapter? = null
    var mCursor: Cursor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCursor = null
        try {
            if (mHlpr == null) {
                mHlpr = CustomDBHelper(applicationContext)
                if (mSqlite == null) {
                    mSqlite = mHlpr!!.writableDatabase
                    if (mSqlite != null) {
                        val values1 = ContentValues()
                        values1.put("name", "Mary")
                        values1.put("age", "20")
                        val i = mSqlite!!.insert("custom", null, values1)
                        val values2 = ContentValues()
                        values2.put("name", "Mirian")
                        values2.put("age", "18")
                        val i2 = mSqlite!!.insert("custom", null, values2)
                        val values3 = ContentValues()
                        values3.put("name", "Mercy")
                        values3.put("age", "16")
                        val i3 = mSqlite!!.insert("custom", null, values3)
                        val projection = arrayOf(
                            "_id",
                            "name",
                            "age"
                        )
                        mCursor = mSqlite!!.query(
                            "custom",
                            projection,
                            null,
                            null,
                            null,
                            null,
                            "age desc"
                        )
                        mAdapter = SimpleCursorAdapter(
                            this,
                            R.layout.list_item,
                            mCursor,
                            arrayOf("name", "age"),
                            intArrayOf(R.id.list_item_name, R.id.list_item_age),
                            0
                        )
                        listview1.adapter = mAdapter
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e("SimpleCursorAdapter_", ex.toString())
        } finally {
            if (mSqlite != null) {
                mSqlite!!.close()
                mSqlite = null
            }
            if (mHlpr != null) {
                mHlpr!!.close()
                mHlpr = null
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mCursor != null) {
            mCursor!!.close()
            mCursor = null
        }
    }
}