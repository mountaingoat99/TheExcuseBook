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
import android.widget.Toast;

import Controllers.DefaultController;
import Utilities.ShakeEventListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ExcuseMe extends ActionBarActivity {

    private Button btnExcuseMe;
    private TextView defaultSportName;
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

        defaultSportName = (TextView)findViewById(R.id.txtDefaultSport);
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
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 2:
                layout.setBackgroundResource(R.mipmap.run_book_closed);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 3:
                layout.setBackgroundResource(R.mipmap.ski_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 4:
                layout.setBackgroundResource(R.mipmap.dive_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 5:
                layout.setBackgroundResource(R.mipmap.swim_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 6:
                layout.setBackgroundResource(R.mipmap.tri_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 7:
                layout.setBackgroundResource(R.mipmap.am_football);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 8:
                layout.setBackgroundResource(R.mipmap.baseball_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 9:
                layout.setBackgroundResource(R.mipmap.football_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 10:
                layout.setBackgroundResource(R.mipmap.golf_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 11:
                layout.setBackgroundResource(R.mipmap.frisbee_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            case 12:
                layout.setBackgroundResource(R.mipmap.basketball_book);
                defaultSportName.setVisibility(View.INVISIBLE);
                break;
            default:
                layout.setBackgroundResource(R.mipmap.default_book);
                defaultSportName.setText(sportName);
                defaultSportName.setVisibility(View.VISIBLE);
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
            case R.id.email_me:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"jeremey.rodriguez@outlook.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Excuse Book Feedback");
                //i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
