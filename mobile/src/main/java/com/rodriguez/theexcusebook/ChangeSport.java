package com.rodriguez.theexcusebook;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Data.DataInterface;


public class ChangeSport extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    int sportId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sport);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Spinner chooseDiveSpinner = (Spinner)findViewById(R.id.spinnerChooseSport);
        chooseDiveSpinner.setOnItemSelectedListener(this);

        DataInterface d = new DataInterface(this);
        LinkedHashMap<Integer, String> sports = d.ExcuseSports();
        ArrayList<String> sportName = new ArrayList<>(sports.values());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, sportName);

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.insert(" Choose a Default Sport", 0);
        chooseDiveSpinner.setAdapter(arrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

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


}
