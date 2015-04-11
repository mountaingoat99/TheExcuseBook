package com.rodriguez.theexcusebook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Data.DataInterface;
import Utilities.OnSpinnerItemClicked;
import Utilities.ShakeEventListener;

public class ExcuseMe extends ActionBarActivity {

    private Button btnExcuseMe;
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    public static int sportId = 0;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excuse_me);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        btnExcuseMe = (Button)findViewById(R.id.btnExcuseMe);

        //loadSavedPreference();
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

    private void loadSavedPreference() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sportId = sp.getInt("sportId", sportId);
        if(sportId > 1){
            // TODO set the sport
        } else {
            // TODO set the default to cycling
        }
    }

    public void savePreferences(String key, int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();

        loadSavedPreference();
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
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_update_sport);
                final Spinner chooseDiveSpinner = (Spinner)dialog.findViewById(R.id.spinnerChooseSport);

                chooseDiveSpinner.setOnItemSelectedListener(new OnSpinnerItemClicked());

                DataInterface d = new DataInterface(this);
                LinkedHashMap<Integer, String> sports = d.ExcuseSports();

                ArrayList<String> sportName = new ArrayList<>(sports.values());


                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                        R.layout.spinner_item, sportName);

                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                arrayAdapter.insert(" Choose a Default Sport", 0);
                chooseDiveSpinner.setAdapter(arrayAdapter);

                dialog.show();
                dialog.setCanceledOnTouchOutside(true);

                break;
            case R.id.add_excuses:
                //TODO show Add dialog
                break;
            case R.id.contact:
                //TODO show contact
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
