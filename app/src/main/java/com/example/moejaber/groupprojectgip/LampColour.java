package com.example.moejaber.groupprojectgip;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 */
public class LampColour extends AppCompatActivity {
    int duration = Toast.LENGTH_SHORT;
    Button green;
    Button blue;
    Button red;
    EditText curColour;
    String KEY = "ProgressBar";
    String saveProgress;
    SharedPreferences.Editor editorLamp;
    SharedPreferences sharedPreferencesLamp;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_colour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        green = (Button) findViewById(R.id.greenButton);
        blue = (Button) findViewById(R.id.blueButton);
        red = (Button) findViewById(R.id.redButton);

        curColour = (EditText) findViewById(R.id.editTextColour);

        sharedPreferencesLamp = PreferenceManager.getDefaultSharedPreferences(this);

    }

    /**
     *
     * @param m
     * @return
     */
    @Override
public boolean onCreateOptionsMenu(Menu m){
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu,m);
    return true;
}

    /**
     *
     * @param mi
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem mi){

        switch (mi.getItemId()){

            case R.id.action_living:
                Log.d("ToolbarGIP","LivingRoom selected");
                Toast.makeText(this,"Living Room - Mouhamad Jaber", duration).show();
                Intent intent = new Intent(LampColour.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_home:
                //Start activity
                Log.d("ToolbarGIP","Home selected");

                intent = new Intent(LampColour.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_kitchen:
                //Start activity
                Log.d("ToolbarGIP","Kitchen selected");
                intent = new Intent(LampColour.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_car:
                //Start activity
                Log.d("ToolbarGIP","Automobile selected");
                intent = new Intent(LampColour.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return false;
    }

    public void red (View view){
        curColour.setText("Red");

    }
    public void green (View view){
        curColour.setText("Green");

    }
    public void blue (View view){
        curColour.setText("Blue");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("LampDimmable", "In onResume()");
        saveProgress = sharedPreferencesLamp.getString("KEY","WHITE");
        curColour.setText(saveProgress);

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("LampDimmable", "In onPause()");
        editorLamp = sharedPreferencesLamp.edit();
        String Progress = curColour.getText().toString();
        editorLamp.putString("KEY",Progress);
        editorLamp.commit();
    }
}


