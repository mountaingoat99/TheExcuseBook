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

import Controllers.DefaultController;
import Controllers.ExcuseController;


public class AddExcuse extends ActionBarActivity {

    private EditText newExcuse;
    private Button btnAddExcuse;
    private String excuseString;
    int sportId;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excuse);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        newExcuse = (EditText)findViewById(R.id.editAddExcuse);
        btnAddExcuse = (Button)findViewById(R.id.btnAddExcuse);

        checkDefaultSport();
        addListenerOnButton();

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
}
