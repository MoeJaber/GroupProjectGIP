package com.example.moejaber.groupprojectgip;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class LampSwitch extends AppCompatActivity {
    //toast duration
    int duration = Toast.LENGTH_SHORT;
    int lampState ;
    int lampState2;
    //initialize database object
    private SQLiteDatabase database;
    //initialize Database class
    private DatabaseHelperLivingRoom dbHelper;

    //ToggleButton initialize
    ToggleButton switchButton;

    /**
     * on create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_switch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switchButton = (ToggleButton) findViewById(R.id.toggleButton);

        dbHelper = new DatabaseHelperLivingRoom(LampSwitch.this);
        database = dbHelper.getWritableDatabase();
        Cursor SelectCursor = dbHelper.lightSwitchSelect();
        while (SelectCursor.moveToNext()) {
            lampState2= SelectCursor.getInt(0);
            Log.d("Lamp Switch in db", Integer.toString(lampState));
            //Log.d("Lamp Switch", Integer.toString(lampState2));

        }
        if (lampState2 == 1) {
            switchButton.setChecked(true);

        }
        else {
            switchButton.setChecked(false);
        }
        LivingRoomSwitch lrf = new LivingRoomSwitch();
        lrf.execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu m){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,m);
        return true;
    }

    /**
     * handle the toolbar options
     * @param mi
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem mi){

        switch (mi.getItemId()){
            //menu bar for living room
            case R.id.action_living:
                Log.d("ToolbarGIP","LivingRoom selected");
                Toast.makeText(this,"Living Room - Mouhamad Jaber", duration).show();
                Intent intent = new Intent(LampSwitch.this, MainActivity.class);
                startActivity(intent);

                break;
            //menu bar for home
            case R.id.action_home:
                //Start activity
                Log.d("ToolbarGIP","Home selected");

                intent = new Intent(LampSwitch.this, MainActivity.class);
                startActivity(intent);

                break;
                //menu bar for kitchen
            case R.id.action_kitchen:
                //Start activity
                Log.d("ToolbarGIP","Kitchen selected");
                intent = new Intent(LampSwitch.this, MainActivity.class);
                startActivity(intent);

                break;
            //menu bar for car
            case R.id.action_car:
                //Start activity
                Log.d("ToolbarGIP","Automobile selected");
                intent = new Intent(LampSwitch.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    /**
     * onclick function for toggle button display toast onclick
     * @param view
     */
    public void lightSwitch(View view){

        LivingRoomSwitch lrf = new LivingRoomSwitch();


        if (switchButton.isChecked() ==true){
            Toast toast = Toast.makeText(this , "Light Switch ON", duration);
            toast.show(); //display your message box
            lampState =1;
            lrf.execute();
        }
        else{
            Toast toast = Toast.makeText(this , "Light Switch OFF", duration);
            toast.show(); //display your message box
            lampState=0;
            lrf.execute();
        }
    }
    /**
     * Async task opens db and stores state of devices
     */
    private class LivingRoomSwitch extends AsyncTask<Object, Object, Cursor> {
        //open db read cursor from database
        @Override
        protected Cursor doInBackground(Object... params) {

            //lampState2=1;
            //open db
            dbHelper = new DatabaseHelperLivingRoom(LampSwitch.this);
            database = dbHelper.getWritableDatabase();
            //grab cursor and store in cursor object
            Cursor SelectCursor = dbHelper.lightSwitchSelect();
            //check database if open
            if(database.isOpen()){
                Log.i("Async","Db is OPEN");
                //dbHelper.mostUsed(database,lampDim2,lampS1,lampColour3,tvR4,windowBlinds5);
                dbHelper.lightSwitch(database,lampState);
                Log.i("Async","Light State Updated");
            }
            //loop through cursor and initialize values
            while (SelectCursor.moveToNext()) {
                lampState2= SelectCursor.getInt(0);
                Log.d("Lamp Switch in db", Integer.toString(lampState));
                //Log.d("Lamp Switch", Integer.toString(lampState2));

            }
            //close the db
            database.close();
            return null;
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);


        }

    }
}
