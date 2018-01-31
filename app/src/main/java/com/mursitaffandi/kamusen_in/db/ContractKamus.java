package com.mursitaffandi.kamusen_in.db;

import android.provider.BaseColumns;

/**
 * Created by mursitaffandi on 12/01/18.
 */

public class ContractKamus {
    public static String TB_ENGLISH = "en_in";
    public static String TB_INDONESIA = "in_en";

    public static final class KamusColumns implements BaseColumns{
        public static String COLUMN_WORD = "word";
        public static String COLUMN_TRANSLATE = "translate";
    }
}
