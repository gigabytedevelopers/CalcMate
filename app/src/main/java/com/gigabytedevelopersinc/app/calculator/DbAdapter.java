package com.gigabytedevelopersinc.app.calculator;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 10/5/2017
 **/
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {

    public static final String KEY_EXP = "exp";
    public static final String KEY_RESULT = "result";
    public static final String KEY_ROWID = "_id";
    private static final String MEMORY_CREATE =
            "create table memory (_id integer primary key autoincrement, "
                    + "exp text not null, result text not null)";
    private static final String HISTORY_CREATE =
            "create table history (_id integer primary key autoincrement, " +
                    "exp text not null, result text not null)";
    private static final String FAV_CREATE =
            "create table fav (_id integer primary key autoincrement, " +
                    "exp text not null, result text not null)";
    private static final String FAVO_CREATE =
            "create table favo (_id integer primary key autoincrement, " +
                    "exp text not null, result text not null, unit text not null)";
    private static final String DATABASE_NAME = "data";
    private static final String TABLE_MEMORY = "memory";
    private static final String TABLE_HISTORY = "history";
    private static final String TABLE_FAV = "fav";
    private static final String TABLE_FAVO = "favo";
    private static final int DATABASE_VERSION = 3;
    private final Context mCtx;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public void createMemory(String exp, String result) {
        String query = "INSERT INTO memory (exp,result) VALUES(\"" + exp + "\",\"" + result + "\")";
        mDb.execSQL(query);
    }

    public void createHistory(String exp, String result) {
        String query = "INSERT INTO history (exp,result) VALUES(\"" + exp + "\",\"" + result + "\")";
        mDb.execSQL(query);
    }

    public void createFav(String exp, String result) {
        String query = "INSERT INTO fav (exp,result) VALUES(\"" + exp + "\",\"" + result + "\")";
        mDb.execSQL(query);
    }

    public void createFav(String exp, String result, String unit) {
        String query = "INSERT INTO favo (exp,result,unit) VALUES(\"" + exp + "\",\"" + result + "\",\"" + unit + "\")";
        mDb.execSQL(query);
    }

    public boolean deleteMemory(long rowId) {

        return mDb.delete(TABLE_MEMORY, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean deleteHistory(long rowId) {

        return mDb.delete(TABLE_HISTORY, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean deleteFav(long rowId, int option) {
        if (option == 1)
            return mDb.delete(TABLE_FAV, KEY_ROWID + "=" + rowId, null) > 0;
        else
            return mDb.delete(TABLE_FAVO, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor fetchAllMemories() {

        String query = "SELECT * FROM " + TABLE_MEMORY + " ORDER BY " + KEY_ROWID + " DESC";
        return mDb.rawQuery(query, null);
    }

    public Cursor fetchAllHistories() {

        String query = "SELECT * FROM " + TABLE_HISTORY + " ORDER BY " + KEY_ROWID + " DESC";
        return mDb.rawQuery(query, null);
    }

    public Cursor fetchAllFavs(int option) {
        if (option == 1) {
            String query = "SELECT * FROM " + TABLE_FAV + " ORDER BY " + KEY_ROWID + " DESC";
            return mDb.rawQuery(query, null);
        } else {
            String query = "SELECT * FROM " + TABLE_FAVO + " ORDER BY " + KEY_ROWID + " DESC";
            return mDb.rawQuery(query, null);
        }
    }

    public String memoryRetrieve() {
        String query = "SELECT * FROM " + TABLE_MEMORY + " ORDER BY " + KEY_ROWID + " DESC";
        Cursor cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            return cursor.getString(1);
        } else
            return "xyz";
    }

    public void deleteAllMemories() {
        mDb.execSQL("DELETE FROM memory");
    }

    public void deleteAllHistories() {
        mDb.execSQL("DELETE FROM history");
    }

    public void deleteAllFavs(int option) {
        if (option == 1)
            mDb.execSQL("DELETE FROM fav");
        else
            mDb.execSQL("DELETE FROM favo");
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(MEMORY_CREATE);
            } catch (Exception e) {
                //
            }
            try {
                db.execSQL(HISTORY_CREATE);
            } catch (Exception e) {
                //
            }
            try {
                db.execSQL(FAV_CREATE);
            } catch (Exception e) {
                //
            }
            try {
                db.execSQL(FAVO_CREATE);
            } catch (Exception e) {
                //
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("DbAdapter : ", "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            onCreate(db);
        }
    }
}
