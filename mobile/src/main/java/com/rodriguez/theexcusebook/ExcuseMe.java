package com.rodriguez.theexcusebook;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Controllers.DefaultController;
import Utilities.ShakeEventListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ExcuseMe extends ActionBarActivity {

    private Button btnExcuseMe;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.primary_dark));
        }

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btnExcuseMe = (Button)findViewById(R.id.btnExcuseMe);

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
        ChangeBackground();
    }

    public void ChangeBackground() {
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.excuse_me_layout);
        switch (sportId){
            case 1:
                layout.setBackgroundResource(R.mipmap.bike_book_closed);
                break;
            case 2:
                layout.setBackgroundResource(R.mipmap.run_book_closed);
                break;
            case 3:
                layout.setBackgroundResource(R.mipmap.ski_book);
                break;
            case 4:
                layout.setBackgroundResource(R.mipmap.dive_book);
                break;
            case 5:
                layout.setBackgroundResource(R.mipmap.swim_book);
                break;
            case 6:
                layout.setBackgroundResource(R.mipmap.tri_book);
                break;
            case 7:
                layout.setBackgroundResource(R.mipmap.am_football);
                break;
        }
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
                Intent intent = new Intent(context, ChangeSport.class);
                startActivity(intent);
                break;
            case R.id.add_excuses:
                Intent intent1 = new Intent(context, AddExcuse.class);
                startActivity(intent1);
                break;
            case R.id.add_sport:
                Intent intent2 = new Intent(context, AddSport.class);
                startActivity(intent2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
