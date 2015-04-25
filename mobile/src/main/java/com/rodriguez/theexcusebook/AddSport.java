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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import Controllers.DefaultController;
import Data.DataInterface;


public class AddSport extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private EditText newSport;
    private Button btnAddSport;
    private String sportString;
    private int sportId;
    final Context context = this;

    private static final String PATH = "/datapathtest";
    private static final String SPORT_KEY = "sportID";
    private static final String TYPE_KEY = "typeKey";
    private static final String NEW_SPORT_KEY = "newSport";
    private static final String TAG = "AddExcuse ";
    private static final String UPDATE_TYPE = "Add_Sport";
    private GoogleApiClient  mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sport);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        newSport = (EditText)findViewById(R.id.editAddSport);
        btnAddSport = (Button)findViewById(R.id.btnAddSport);

        addListenerOnButton();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
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

        btnAddSport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sportString = newSport.getText().toString().trim();
                    if (!sportString.isEmpty()) {

                        // here we will add the new sport and get the ID back
                        sportId = DefaultController.AddNewSport(sportString, context);
                        // then we will go ahead and update the default sport to the new one
                        DataInterface di = new DataInterface(context);
                        di.UpdateDefaultSport(sportId);

                        // make sure the request is running and update it on the watch
                        if(mGoogleApiClient.isConnected()) {

                            PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(PATH);

                            // add data to the request
                            putDataMapRequest.getDataMap().putInt(SPORT_KEY, sportId);
                            putDataMapRequest.getDataMap().putString(NEW_SPORT_KEY, sportString);
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

                        Toast.makeText(getApplicationContext(),
                                "New Sport: " + sportString + " was entered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ExcuseMe.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "No Sport was entered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ExcuseMe.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_add_sport, menu);
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
