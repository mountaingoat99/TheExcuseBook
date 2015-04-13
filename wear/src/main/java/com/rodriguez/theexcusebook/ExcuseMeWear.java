package com.rodriguez.theexcusebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ExcuseMeWear extends Activity {

    private Button mbtnExcuseMe;
    public static final String TAG = "MAIN_ACTIVITY";
    private int sportId = 0;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity Has Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excuse_me_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);

        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mbtnExcuseMe = (Button) stub.findViewById(R.id.btnExcuseMe);
            }
        });

        addListenerOnButton();
    }

    public void addListenerOnButton(){

        mbtnExcuseMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowExcuseWear.class);
                Bundle b = new Bundle();
                b.putInt("sportId", sportId);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
