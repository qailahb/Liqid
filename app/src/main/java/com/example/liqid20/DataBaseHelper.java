package com.example.liqid20;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "Liqid2.0";
    public static final String TABLE_NAME = "project";
    public static final String COL_1 = "SET";
    public static final String COL_2 = "SPEED";
    public static final String COL_3 = "TRAVEL";
    public static final String COL_4 = "WAIT";
    public static final String COL_5 = "FORCE";

    SQLiteDatabase dB = this.getWritableDatabase();

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase dB) {
        dB.execSQL("CREATE TABLE " + TABLE_NAME + "(COL_1 INTEGER PRIMARY KEY AUTOINCREMENT, SPEED TEXT, TRAVEL TEXT, WAIT TEXT, FORCE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dB, int oldVersion, int newVersion) {
        dB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(dB);
    }

    public boolean insertData(String speed, String travel, String wait, String force) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, speed);
        contentValues.put(COL_3, travel);
        contentValues.put(COL_4, wait);
        contentValues.put(COL_5, force);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
