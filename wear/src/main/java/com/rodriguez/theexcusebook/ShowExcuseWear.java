package com.rodriguez.theexcusebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import java.util.List;

import Controllers.ExcuseController;

public class ShowExcuseWear extends Activity {

    //private TextView mTextView;
    private int sportId;
    private String showExcuse = null;
    private final Context context = this;

    //private static final int SPEECH_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_excuse);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                //mTextView = (TextView) stub.findViewById(R.id.txtExcusetext);

                Bundle b = getIntent().getExtras();
                if (b != null) {
                    sportId = b.getInt("sportId");
                }

                //displaySpeechRecognizer();

                NewExcuse();
            }
        });
    }

    // Create an intent that can start the Speech Recognizer activity
//    private void displaySpeechRecognizer() {
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        // Start the activity, the intent will be populated with the speech text
//        startActivityForResult(intent, SPEECH_REQUEST_CODE);
//    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent data) {
//        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
//            List<String> results = data.getStringArrayListExtra(
//                    RecognizerIntent.EXTRA_RESULTS);
//            String spokenText = results.get(0);
//            // Do something with spokenText
//            if (spokenText.equals("ExcuseMe")){
//                NewExcuse();
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    private void NewExcuse() {
        final TextView mTextView = (TextView)findViewById(R.id.txtExcusetext);
        ExcuseController ec = new ExcuseController();
        showExcuse = ec.FindExcuse(sportId, context);
        mTextView.setText(showExcuse);
    }
}
