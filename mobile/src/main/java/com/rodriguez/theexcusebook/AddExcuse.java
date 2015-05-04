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
import Controllers.ExcuseController;


public class AddExcuse extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener {

    private EditText newExcuse;
    private Button btnAddExcuse;
    private String excuseString;
    private int sportId;
    final Context context = this;

    private static final String PATH = "/datapathtest";
    private static final String SPORT_KEY = "sportID";
    private static final String NEW_EXCUSE_KEY = "newExcuse";
    private static final String TYPE_KEY = "typeKey";
    private static final String TAG = "AddExcuse ";
    private static final String UPDATE_TYPE = "Add_Excuse";
    private GoogleApiClient  mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excuse);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        newExcuse = (EditText)findViewById(R.id.editAddExcuse);
        btnAddExcuse = (Button)findViewById(R.id.btnAddExcuse);

        checkDefaultSport();
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

    public void checkDefaultSport() {
        sportId = DefaultController.DefaultSport(this);
        Log.e("Default Sport is", String.valueOf(sportId));
    }

    private void addListenerOnButton() {

        btnAddExcuse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    excuseString = newExcuse.getText().toString().trim();
                    if (!excuseString.isEmpty()){

                        // make sure the request is running and update it on the watch
                        if(mGoogleApiClient.isConnected()) {

                            PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(PATH);

                            // add data to the request
                            putDataMapRequest.getDataMap().putInt(SPORT_KEY, sportId);
                            putDataMapRequest.getDataMap().putString(NEW_EXCUSE_KEY, excuseString);
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

                        ExcuseController.AddExcuse(sportId, excuseString, context);

                        Toast.makeText(getApplicationContext(),
                                "New Excuse: " + excuseString + " was entered" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ExcuseMe.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "No Excuse was entered", Toast.LENGTH_SHORT).show();
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
        //getMenuInflater().inflate(R.menu.menu_add_excuse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
        //}

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
