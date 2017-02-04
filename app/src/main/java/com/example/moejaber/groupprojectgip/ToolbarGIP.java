package com.example.moejaber.groupprojectgip;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

public class ToolbarGIP extends AppCompatActivity {

    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Toast.makeText(this,"Living Room - Mouhamad Jaber", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ToolbarGIP.this, LivingRoomFragment.class);
                startActivity(intent);

                break;

            case R.id.action_home:
                //Start activity
                Log.d("ToolbarGIP","Home selected");

                intent = new Intent(ToolbarGIP.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_kitchen:
                //Start activity
                Log.d("ToolbarGIP","Kitchen selected");
                intent = new Intent(ToolbarGIP.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_car:
                //Start activity
                Log.d("ToolbarGIP","Automobile selected");
                intent = new Intent(ToolbarGIP.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return false;
    }
}

