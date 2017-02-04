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
import android.widget.SeekBar;
import android.widget.Toast;

public class LampDimmable extends AppCompatActivity {
    int duration = Toast.LENGTH_SHORT;;
    String KEY = "ProgressBar";
    SeekBar lightDim;
    int saveProgress;
    SharedPreferences.Editor editorDim;
    SharedPreferences sharedPreferencesDim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_dimmable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lightDim = (SeekBar) findViewById(R.id.seekBar);
        sharedPreferencesDim = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,m);
        return true;
    }

    //onClick Handler for the toolbar
    public boolean onOptionsItemSelected(MenuItem mi){

        switch (mi.getItemId()){

            case R.id.action_living:
                Log.d("ToolbarGIP","LivingRoom selected");
                Toast.makeText(this,"Living Room - Mouhamad Jaber", duration).show();

                Intent intent = new Intent(LampDimmable.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_home:
                //Start activity
                Log.d("ToolbarGIP","Home selected");

                intent = new Intent(LampDimmable.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_kitchen:
                //Start activity
                Log.d("ToolbarGIP","Kitchen selected");
                intent = new Intent(LampDimmable.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_car:
                //Start activity
                Log.d("ToolbarGIP","Automobile selected");
                intent = new Intent(LampDimmable.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("LampDimmable", "In onResume()");
        saveProgress = sharedPreferencesDim.getInt("Dim",0);
        lightDim.setProgress(saveProgress);

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("LampDimmable", "In onStart()");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("LampDimmable", "In onPause()");
        editorDim = sharedPreferencesDim.edit();
        int Progress = lightDim.getProgress();
        editorDim.putInt("Dim",Progress);
        editorDim.commit();
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("LampDimmable", "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("LampDimmable", "In onDestroy()");
    }
}
