package com.mursitaffandi.kamusen_in.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mursitaffandi.kamusen_in.R;
import com.mursitaffandi.kamusen_in.model.MKamus;

import java.util.ArrayList;

/**
 * Created by mursitaffandi on 13/01/18.
 */

public class AdapterWord extends RecyclerView.Adapter<HolderItemWord> {
    ArrayList<MKamus> kamusArrayList;

    public AdapterWord() {
    }

    public void swaplist(ArrayList<MKamus> kamusArrayList) {
        this.kamusArrayList = kamusArrayList;

notifyDataSetChanged();
    }

    @Override
    public HolderItemWord onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HolderItemWord(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_word, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(HolderItemWord holder, int position) {
holder.setValueView(kamusArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return kamusArrayList.size();
    }
}
