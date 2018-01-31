package com.mursitaffandi.kamusen_in.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.mursitaffandi.kamusen_in.model.MKamus;

import java.util.ArrayList;

/**
 * Created by mursitaffandi on 12/01/18.
 */

public class DMLKamus {

    private Context context;
    private DDLKamus databaseHelper;
    private SQLiteDatabase database;

    public DMLKamus(Context context) {
        this.context = context;
    }

    public DMLKamus open() throws SQLException {
        databaseHelper = new DDLKamus(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public void insertTransaction(ArrayList<MKamus> kamusModels, boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? ContractKamus.TB_ENGLISH : ContractKamus.TB_INDONESIA;
        String sql = "INSERT INTO " + DATABASE_TABLE + " (" +
                ContractKamus.KamusColumns.COLUMN_WORD + ", " +
                ContractKamus.KamusColumns.COLUMN_TRANSLATE+ ") VALUES (?, ?)";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);
        for (int i = 0; i < kamusModels.size(); i++) {
            stmt.bindString(1, kamusModels.get(i).getWord());
            stmt.bindString(2, kamusModels.get(i).getTranslate());
            stmt.execute();
            stmt.clearBindings();
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }
    public ArrayList<MKamus> getAllWordBaseCountry(boolean isEnglish, String keyword){
        Cursor cursor = queryDB(isEnglish, keyword);
        cursor.moveToFirst();
        ArrayList<MKamus> arrayList = new ArrayList<>();
        MKamus mKamus;
        if (cursor.getCount()>0) {
            do {
                mKamus = new MKamus();
                mKamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ContractKamus.KamusColumns._ID)));
                mKamus.setWord(cursor.getString(cursor.getColumnIndexOrThrow(ContractKamus.KamusColumns.COLUMN_WORD)));
                mKamus.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(ContractKamus.KamusColumns.COLUMN_TRANSLATE)));

                arrayList.add(mKamus);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Cursor queryDB(boolean isEnglish, String keyword) {
        Cursor cursor;
        String DATABASE_TABLE = isEnglish ? ContractKamus.TB_ENGLISH : ContractKamus.TB_INDONESIA;
        if (keyword.isEmpty()){
            cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + ContractKamus.KamusColumns._ID+ " ASC", null);
        } else {
            cursor = database.rawQuery("SELECT * FROM "
                    + DATABASE_TABLE
                    + " WHERE "
                    + ContractKamus.KamusColumns.COLUMN_WORD
                    + " LIKE '%"
                    + keyword.trim()
                    + "%'", null);
        }
        return cursor;
    }
}
