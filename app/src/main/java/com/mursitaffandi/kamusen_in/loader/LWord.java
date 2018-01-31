package com.mursitaffandi.kamusen_in.loader;

import android.content.Context;
import android.database.SQLException;

import com.mursitaffandi.kamusen_in.db.DMLKamus;
import com.mursitaffandi.kamusen_in.model.MKamus;
import com.mursitaffandi.kamusen_in.pulse.OnGetItemWord;

import java.util.ArrayList;

/**
 * Created by mursitaffandi on 13/01/18.
 */

public class LWord {
    OnGetItemWord onGetItemWord;
    Context context;

    public LWord(OnGetItemWord onGetItemWord, Context context) {
        this.onGetItemWord = onGetItemWord;
        this.context = context;
    }

    public void loadData(boolean isEnglish, String search) {
         DMLKamus dmlKamus = new DMLKamus(context);
         ArrayList<MKamus> list = new ArrayList<>();
        try {
            dmlKamus.open();
            list = dmlKamus.getAllWordBaseCountry(isEnglish,search);
            onGetItemWord.success(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dmlKamus.close();
        }
    }
}
