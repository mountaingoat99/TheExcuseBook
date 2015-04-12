package com.rodriguez.theexcusebook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import Controllers.ExcuseController;

public class ShowExcuseWear extends Activity {

    private TextView mTextView;
    private int sportId;
    private String showExcuse = null;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_excuse);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.txtExcusetext);
            }
        });

        Bundle b = getIntent().getExtras();
        if(b != null){
            sportId = b.getInt("sportId");
        }

        NewExcuse();
    }

    private void NewExcuse() {
        ExcuseController ec = new ExcuseController();
        showExcuse = ec.FindExcuse(sportId, context);
        mTextView.setText(showExcuse);
    }
}
