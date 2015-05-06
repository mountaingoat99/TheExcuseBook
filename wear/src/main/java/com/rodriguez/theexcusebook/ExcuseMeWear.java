package com.rodriguez.theexcusebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import Controllers.DefaultController;

public class ExcuseMeWear extends Activity  {

    private Button mbtnExcuseMe;
    public static final String TAG = "MAIN_ACTIVITY";
    private int sportId = 0;
    private final Context context = this;
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter

            if (mAccel > 3) {

                Intent intent = new Intent(context, ShowExcuseWear.class);
                Bundle b = new Bundle();
                b.putInt("sportId", sportId);
                intent.setFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(b);
                startActivity(intent);

            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

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
                        intent.setFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        checkDefaultSport();
    }

    public void checkDefaultSport() {
        sportId = DefaultController.DefaultSport(context);
        Log.e("Default SportID is", String.valueOf(sportId));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}
