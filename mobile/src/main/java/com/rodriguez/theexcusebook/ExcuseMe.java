package com.rodriguez.theexcusebook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import Controllers.DefaultController;
import Utilities.ShakeEventListener;

public class ExcuseMe extends ActionBarActivity {

    private Button btnExcuseMe;
    private TextView txtSportName;
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    public static int sportId = 0;
    public static String sportName = "";
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excuse_me);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        btnExcuseMe = (Button)findViewById(R.id.btnExcuseMe);
        txtSportName = (TextView)findViewById(R.id.txtSportName);

        checkDefaultSport();
        addListenerOnButton();

        //shake event
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
                Intent intent  = new Intent(context, showExcuse.class);
                Bundle b = new Bundle();
                b.putInt("sportId", sportId);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    public void checkDefaultSport() {
        sportId = DefaultController.DefaultSport(context);
        sportName = DefaultController.DefaultSportName(sportId, context);
        Log.e("Default SportID is", String.valueOf(sportId));
        Log.e("Default SportName is", sportName);
        txtSportName.setText(sportName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void addListenerOnButton() {
        btnExcuseMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, showExcuse.class);
                Bundle b = new Bundle();
                b.putInt("sportId", sportId);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_excuse_me, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.change_sport:
                //TODO show change dialog
                Intent intent = new Intent(context, ChangeSport.class);
                startActivity(intent);
                break;
            case R.id.add_excuses:
                //TODO show Add dialog
                break;
            case R.id.add_sport:
                //TODO show add sport dialog
                break;
            case R.id.contact:
                //TODO show contact
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
