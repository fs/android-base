package com.flatsoft.base.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String NAME = "database.sqlite3";
    static final int    VERSION = 1;

    static {
        // register your models here
        // cupboard().register(Model.class);
    }

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
