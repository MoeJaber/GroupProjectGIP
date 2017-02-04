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

public class WindowBlinds extends AppCompatActivity {
    int duration = Toast.LENGTH_SHORT;
    String KEY = "ProgressBar";
    int saveProgress;
    SeekBar dimmer;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_blinds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dimmer = (SeekBar) findViewById(R.id.seekBar2);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

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
                Intent intent = new Intent(WindowBlinds.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_home:
                //Start activity
                Log.d("ToolbarGIP","Home selected");

                intent = new Intent(WindowBlinds.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_kitchen:
                //Start activity
                Log.d("ToolbarGIP","Kitchen selected");
                intent = new Intent(WindowBlinds.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_car:
                //Start activity
                Log.d("ToolbarGIP","Automobile selected");
                intent = new Intent(WindowBlinds.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return false;
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("WindowBlinds", "In onResume()");
        int save;
        save = sharedPreferences.getInt("pref",0);
        dimmer.setProgress(save);

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("WindowBlinds", "In onStart()");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("WindowBlinds", "In onPause()");
        editor = sharedPreferences.edit();
        int Progress = dimmer.getProgress();
        editor.putInt("pref",Progress);
        editor.commit();
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("WindowBlinds", "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("WindowBlinds", "In onDestroy()");
    }
}
