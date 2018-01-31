package com.mursitaffandi.kamusen_in.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mursitaffandi on 11/01/18.
 */

public class MKamus implements Parcelable {
    private int id;
    private String word;
    private String translate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public MKamus(int id, String word, String translate) {

        this.id = id;
        this.word = word;
        this.translate = translate;
    }
    public MKamus() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.translate);
    }

    protected MKamus(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.translate = in.readString();
    }

    public static final Parcelable.Creator<MKamus> CREATOR = new Parcelable.Creator<MKamus>() {
        @Override
        public MKamus createFromParcel(Parcel source) {
            return new MKamus(source);
        }

        @Override
        public MKamus[] newArray(int size) {
            return new MKamus[size];
        }
    };

    public MKamus(String word, String translate) {
        this.word = word;
        this.translate = translate;
    }
}
