package com.rodriguez.theexcusebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import Controllers.DefaultController;

public class ExcuseMeWear extends Activity implements SensorEventListener {

    private Button mbtnExcuseMe;
    public static final String TAG = "MAIN_ACTIVITY";
    private int sportId = 0;
    private final Context context = this;
    private SensorManager mSensorManager;
    private Sensor mShake;

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
                mbtnExcuseMe.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ShowExcuseWear.class);
                        Bundle b = new Bundle();
                        b.putInt("sportId", sportId);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
            }
        });

        mSensorManager = (SensorManager) getSystemService(context.SENSOR_SERVICE);
        mShake = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);

        checkDefaultSport();
    }

    public void checkDefaultSport() {
        sportId = DefaultController.DefaultSport(context);
        Log.e("Default SportID is", String.valueOf(sportId));
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Intent intent = new Intent(context, ShowExcuseWear.class);
        Bundle b = new Bundle();
        b.putInt("sportId", sportId);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
       mSensorManager.registerListener(this, mShake, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
