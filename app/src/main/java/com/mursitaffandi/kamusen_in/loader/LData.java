package com.mursitaffandi.kamusen_in.loader;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.os.AsyncTask;

import com.mursitaffandi.kamusen_in.R;
import com.mursitaffandi.kamusen_in.db.DMLKamus;
import com.mursitaffandi.kamusen_in.model.MKamus;
import com.mursitaffandi.kamusen_in.pulse.FirstLoad;
import com.mursitaffandi.kamusen_in.view.MainActivity;
import com.mursitaffandi.kamusen_in.view.PreloadActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by mursitaffandi on 13/01/18.
 */

public class LData extends AsyncTask<Void, Integer, Void> {
    DMLKamus kamusHelper;
    Context mContext;
    FirstLoad firstLoad;

    public LData(Context mContext, FirstLoad firstLoad) {
        this.mContext = mContext;
        this.firstLoad = firstLoad;
    }

    @Override
    protected void onPreExecute() {
        kamusHelper = new DMLKamus(mContext);
    }

    @SuppressWarnings("WrongThread")
    @Override
    protected Void doInBackground(Void... params) {
        ArrayList<MKamus> english_indonesia = preLoadRaw(R.raw.english_indonesia);
        ArrayList<MKamus> indonesia_english = preLoadRaw(R.raw.indonesia_english);


        try {
            kamusHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        kamusHelper.insertTransaction(english_indonesia, true);
        kamusHelper.insertTransaction(indonesia_english, false);
        kamusHelper.close();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        firstLoad.onFinishLoad();
    }

    public ArrayList<MKamus> preLoadRaw(int fileResource) {
        ArrayList<MKamus> mKamuses = new ArrayList<>();
        BufferedReader reader;
        try {
            Resources res = mContext.getResources();
            InputStream raw_dict = res.openRawResource(fileResource);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            String line = null;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                MKamus mKamus;
                mKamus = new MKamus(splitstr[0], splitstr[1]);
                mKamuses.add(mKamus);
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mKamuses;
    }
}
