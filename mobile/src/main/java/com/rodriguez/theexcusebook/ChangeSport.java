package com.rodriguez.theexcusebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Controllers.DefaultController;
import Data.DataInterface;


public class ChangeSport extends ActionBarActivity implements AdapterView.OnItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    int sportId;
    private Spinner chooseDiveSpinner;
    private Button btnUpdateSport;
    final Context context = this;

    private static final String PATH = "/datapathtest";
    private static final String SPORT_KEY = "sportID";
    private static final String TYPE_KEY = "typeKey";
    private static final String TAG = "ChangeSport ";
    private static final String UPDATE_TYPE = "Change_Sport";
    private GoogleApiClient  mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sport);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        chooseDiveSpinner = (Spinner)findViewById(R.id.spinnerChooseSport);
        btnUpdateSport = (Button)findViewById(R.id.btnUpdateSport);

        chooseDiveSpinner.setOnItemSelectedListener(this);

        SetSpinnerData();
        checkDefaultSport();
        addListenerOnButton();

        mGoogleApiClient = new Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onDestroy(){
        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }

        super.onDestroy();
    }

    private void addListenerOnButton() {
        btnUpdateSport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DataInterface di = new DataInterface(context);
                if (sportId > 0) {

                    // make sure the request is running and update it on the watch
                    if(mGoogleApiClient.isConnected()) {

                        PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(PATH);

                        // add data to the request
                        putDataMapRequest.getDataMap().putInt(SPORT_KEY, sportId);
                        putDataMapRequest.getDataMap().putString(TYPE_KEY, UPDATE_TYPE);
                        PutDataRequest request = putDataMapRequest.asPutDataRequest();

                        Wearable.DataApi.putDataItem(mGoogleApiClient, request)
                                .setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                                    @Override
                                    public void onResult(DataApi.DataItemResult dataItemResult) {
                                        Log.d(TAG, "putDataItem status: "
                                                + dataItemResult.getStatus().toString());
                                    }
                                });
                    }

                    // always update the record on the phone
                    di.UpdateDefaultSport(sportId);
                }
                Intent intent = new Intent(context, ExcuseMe.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void checkDefaultSport() {
        sportId = DefaultController.DefaultSport(this);
        Log.e("Default Sport is", String.valueOf(sportId));
    }

    private void SetSpinnerData() {
        DataInterface d = new DataInterface(this);
        LinkedHashMap<Integer, String> sports = d.ExcuseSports();
        ArrayList<String> sportName = new ArrayList<>(sports.values());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, sportName);

        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        chooseDiveSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        sportId = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_change_sport, menu);
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


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
