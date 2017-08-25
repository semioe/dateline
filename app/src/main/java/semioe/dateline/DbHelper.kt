package semioe.dateline
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbHelper(baseContext: Context) {

    private lateinit var db:SQLiteDatabase

    init {
        var dbName="semioe"
        db= SQLiteDatabase.openOrCreateDatabase(baseContext.filesDir.path+"$dbName.db",null);
    }
    fun exec(sql:String){
        db.execSQL(sql)
    }


    //是否存在表
    fun isTableExist(tableName: String): Boolean {
        val sql = "select count(*) as c from sqlite_master where type ='table' and name ='$tableName' "
        var flag=false
        var cursor = db.rawQuery(sql, null)
        if (cursor.moveToNext()) {
            val count = cursor.getInt(0)
            if (count > 0) {
                //存在
                flag=true
            }
        }
        cursor.close()
        return flag
    }

    fun cursor(sql: String): Cursor? {
        return db.rawQuery(sql, null)
    }

    fun count(sql: String): Int {
        return cursor(sql)!!.count
    }

    //是否存在字段
    private fun isFieldExist(tableName: String, columnName: String): Boolean {
        var result = false
        var cursor: Cursor? = null
        try {
            //查询一行
            cursor = db.rawQuery("SELECT * FROM $tableName LIMIT 0", null)
            result = cursor != null && cursor.getColumnIndex(columnName) != -1
        } catch (e: Exception) {
            //Log.e(TAG, "checkColumnExists1..." + e.message)
        } finally {
            if (null != cursor && !cursor.isClosed) {
                cursor.close()
            }
        }
        return result
    }

    fun closeDB(){
        db.close()
    }
}