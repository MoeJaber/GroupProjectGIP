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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TvRemote extends AppCompatActivity {
    int duration = Toast.LENGTH_SHORT;

    Button upBtn;
    Button leftBtn;
    Button downBtn;
    Button rightBtn;
    ToggleButton powerBtn;
    EditText channelNum;
    TextView channelDisplay;
    int counter = 1;
    String saveProgressTV;
    SharedPreferences.Editor editorTV;
    SharedPreferences sharedPreferencesTV;
    String KEY = "Channel";
    String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_remote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        upBtn = (Button) findViewById(R.id.upButton);
        leftBtn = (Button) findViewById(R.id.leftButton);
        rightBtn = (Button) findViewById(R.id.rightButton);
        downBtn = (Button) findViewById(R.id.downButton);
        powerBtn = (ToggleButton) findViewById(R.id.powerButton);
        channelNum= (EditText) findViewById(R.id.editText2);
        channelDisplay = (TextView) findViewById(R.id.textView3);

        sharedPreferencesTV = PreferenceManager.getDefaultSharedPreferences(this);



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
                Intent intent = new Intent(TvRemote.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_home:
                //Start activity
                Log.d("ToolbarGIP","Home selected");

                intent = new Intent(TvRemote.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_kitchen:
                //Start activity
                Log.d("ToolbarGIP","Kitchen selected");
                intent = new Intent(TvRemote.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.action_car:
                //Start activity
                Log.d("ToolbarGIP","Automobile selected");
                intent = new Intent(TvRemote.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return false;
    }

    public void upClick(View view){
        channelDisplay.setText(Integer.toString(counter));
        counter++;
    }
    public void downClick(View view){
        channelDisplay.setText(Integer.toString(counter));
        counter--;
    }
    public void leftClick(View view){
        channelDisplay.setText(Integer.toString(counter));
        counter--;
    }
    public void rightClick(View view){
        channelDisplay.setText(Integer.toString(counter));
        counter++;
    }

    public void channelEnter(View view){
        String Number;
        Number= channelNum.getText().toString();
        channelDisplay.setText(Number);

    }

    public void power(View view){
        if (channelDisplay.getVisibility() == View.VISIBLE) {
            channelDisplay.setVisibility(View.INVISIBLE);
        }
        else {
            channelDisplay.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("TvREMOTE", "In onResume()");
        saveProgressTV = sharedPreferencesTV.getString(KEY,"1");
        channelDisplay.setText(saveProgressTV);
        temp = channelDisplay.getText().toString();
        counter = new Integer(temp).intValue();
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("TvREMOTE", "In onPause()");
        editorTV = sharedPreferencesTV.edit();
        String Progress = channelDisplay.getText().toString();
        editorTV.putString(KEY,Progress);
        editorTV.commit();
    }



}
