package com.mursitaffandi.kamusen_in.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mursitaffandi on 12/01/18.
 */

public class DDLKamus extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "db_kamus";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_EN_IN = "create table " + ContractKamus.TB_ENGLISH + " (" +
            ContractKamus.KamusColumns._ID + " integer primary key autoincrement, " +
            ContractKamus.KamusColumns.COLUMN_WORD + " text not null, " +
            ContractKamus.KamusColumns.COLUMN_TRANSLATE + " text not null);";

    public static String CREATE_TABLE_IN_EN = "create table " + ContractKamus.TB_INDONESIA + " (" +
            ContractKamus.KamusColumns._ID + " integer primary key autoincrement, " +
            ContractKamus.KamusColumns.COLUMN_WORD + " text not null, " +
            ContractKamus.KamusColumns.COLUMN_TRANSLATE + " text not null);";

    public DDLKamus(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EN_IN);
        db.execSQL(CREATE_TABLE_IN_EN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ContractKamus.TB_ENGLISH);
        db.execSQL("DROP TABLE IF EXISTS "+ContractKamus.TB_INDONESIA);
        onCreate(db);
    }
}
