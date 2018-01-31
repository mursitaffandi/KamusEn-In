package com.mursitaffandi.kamusen_in.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mursitaffandi.kamusen_in.R;
import com.mursitaffandi.kamusen_in.model.MKamus;
import com.mursitaffandi.kamusen_in.view.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mursitaffandi on 13/01/18.
 */

class HolderItemWord extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_item_word)
    TextView tv_word;

    @BindView(R.id.tv_item_translate)
    TextView tv_translate;

    public HolderItemWord(View inflate) {
        super(inflate);
        ButterKnife.bind(this, inflate);
    }
    public void setValueView(final MKamus item) {
        tv_word.setText(item.getWord());
        tv_translate.setText(item.getTranslate());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.ITEM_ID, item);
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
