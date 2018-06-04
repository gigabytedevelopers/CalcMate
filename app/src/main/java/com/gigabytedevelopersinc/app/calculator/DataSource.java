package com.gigabytedevelopersinc.app.calculator;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getConstants() {

        List<String> constants = new ArrayList<String>();
        String query = "SELECT * FROM " + DataBaseHelper.TABLE_CONSTANTS + " ORDER BY " + DataBaseHelper.COLUMN_NAME + " ASC";
        return database.rawQuery(query, null);
    }
}
