package com.rodriguez.theexcusebook;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import Controllers.ExcuseController;
import Utilities.ShakeEventListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class showExcuse extends ActionBarActivity {

    private Button btnExcuseMe;
    private TextView txtShowExcuse;
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    private int sportId;
    private String showExcuse = null;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_excuse);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.primary_dark));
        }

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        txtShowExcuse = (TextView)findViewById(R.id.txtExcuse);
        btnExcuseMe = (Button)findViewById(R.id.btnExcuseMe);

        Bundle b = getIntent().getExtras();
        if(b != null){
            sportId = b.getInt("sportId");
        }

        //shake event
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
                NewExcuse();
            }
        });

        addListenerOnButton();
        NewExcuse();
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

    private void addListenerOnButton(){
        btnExcuseMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewExcuse();
            }
        });
    }

    private void NewExcuse() {
        ExcuseController ec = new ExcuseController();
        showExcuse = ec.FindExcuse(sportId, context);
        txtShowExcuse.setText(showExcuse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_show_excuse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
