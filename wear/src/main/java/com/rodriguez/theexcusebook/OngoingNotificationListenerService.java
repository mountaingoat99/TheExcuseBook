package com.rodriguez.theexcusebook;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import Data.DataInterface;

public class OngoingNotificationListenerService extends WearableListenerService {

    private static final int NOTIFICATION_ID = 897091;
    private GoogleApiClient mGoogleApiClient;
    private String TAG = "OngoingNotificationListenerService";
    private static final String PATH = "/datapathtest";
    private static final String SPORT_KEY = "sportID";

    final Context context = this;
    private int sportID;

    public OngoingNotificationListenerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        final List<DataEvent> events = FreezableUtils.freezeIterable(dataEvents);
        dataEvents.close();

        if (!mGoogleApiClient.isConnected()) {
            ConnectionResult connectionResult = mGoogleApiClient
                    .blockingConnect(30, TimeUnit.SECONDS);
            if (!connectionResult.isSuccess()) {
                Log.e(TAG, "Service failed to connect to GoogleApiClient.");
                return;
            }
        }

        for(DataEvent event : events){
            if (event.getType() == DataEvent.TYPE_CHANGED){
                String path = event.getDataItem().getUri().getPath();
                if(PATH.equals(path)) {
                    // get the data from the event
                    DataMapItem dataMapItem =
                            DataMapItem.fromDataItem(event.getDataItem());
                    sportID = dataMapItem.getDataMap().getInt(SPORT_KEY);

                    // write the new record to the Default Sport Table
                    DataInterface di = new DataInterface(context);
                    di.UpdateDefaultSport(sportID);

                } else {
                    Log.d(TAG, "Unrecognized path: " + path);
                }
            }
        }
    }
}
