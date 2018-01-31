package com.mursitaffandi.kamusen_in.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.mursitaffandi.kamusen_in.R;
import com.mursitaffandi.kamusen_in.loader.LData;
import com.mursitaffandi.kamusen_in.pulse.FirstLoad;
import butterknife.ButterKnife;

import static com.mursitaffandi.kamusen_in.MyApplication.isFirstTime;


public class PreloadActivity extends AppCompatActivity implements FirstLoad {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist_load);
        ButterKnife.bind(this);
        if (isFirstTime())
        new LData(this, this).execute();
        else this.onFinishLoad();
    }

    @Override
    public void onFinishLoad() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
