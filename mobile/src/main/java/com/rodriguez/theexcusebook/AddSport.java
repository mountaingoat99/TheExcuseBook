package com.rodriguez.theexcusebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controllers.DefaultController;
import Data.DataInterface;


public class AddSport extends ActionBarActivity {

    private EditText newSport;
    private Button btnAddSport;
    private String sportString;
    private int sportId;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sport);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        newSport = (EditText)findViewById(R.id.editAddSport);
        btnAddSport = (Button)findViewById(R.id.btnAddSport);

        addListenerOnButton();
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
}
