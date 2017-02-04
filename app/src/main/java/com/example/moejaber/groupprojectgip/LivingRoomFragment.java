package com.example.moejaber.groupprojectgip;

import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by MoeJaber on 2016-11-28.
 */
public class LivingRoomFragment extends ListFragment {
    //array from ListView
    String Options[] = {"LAMP SWITCH","LAMP DIMMABLE","LAMP COLOUR CHANGE","TELEVISION REMOTE","WINDOW BLINDS", "INSTRUCTIONS"};
    //toast duration
    int duration = Toast.LENGTH_SHORT;

    //Variables for incrementing db values
    int lampS1=0;
    int lampDim2=0;
    int lampColour3=0;
    int tvR4=0;
    int windowBlinds5=0;
    //Retrieve current db values and store them
    int lampS;
    int lampDim;
    int lampColour;
    int tvR;
    int windowBlinds;

    //initialize ArrayAdapter
    private ArrayAdapter<String> adapter;
    //initialize database object
    private SQLiteDatabase database;
    //initialize Database class
    private DatabaseHelperLivingRoom dbHelper;
    //Alert Dialog message
    String OK = "OK";

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,Options);
        setListAdapter(adapter);

        //execute asynctask
        LivingRoomFrag lrf = new LivingRoomFrag();
        lrf.execute();
    }

    /**
     * create the fragment and display
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment_room, container, false);
        return rootView;
    }

    /**
     * handle the list view rows and perform tasks depending on which row is selected
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        LivingRoomFrag lrf = new LivingRoomFrag();

        switch (position){
            //Lamp switch activity
            case 0: Log.d("ListFragment","LAMP SWITCH");
                Log.d("ListFragment",Integer.toString(lampS1));
                Toast.makeText(getActivity().getApplicationContext(), "LAMP CONTROL, by Mouhamad Jaber", duration).show();
                lampS1++;
                lrf.execute();
                Intent intent = new Intent(getActivity().getApplicationContext(), LampSwitch.class);
                startActivity(intent);
                break;
            //Lamp switch activity
            case 1: Log.d("ListFragment","LAMP DIMMABLE"); Log.d("ListFragment",Integer.toString(lampDim2));
                Toast.makeText(getActivity().getApplicationContext(), "DIMMABLE CONTROL, by Mouhamad Jaber", duration).show();
                lampDim2++;
                lrf.execute();
                intent = new Intent(getActivity().getApplicationContext(), LampDimmable.class);
                startActivity(intent);
                break;
            //lamp colour activity
            case 2: Log.d("ListFragment","LAMP COLOUR CHANGE"); Log.d("ListFragment",Integer.toString(lampColour3));
                Toast.makeText(getActivity().getApplicationContext(), "COLOUR REMOTE, by Mouhamad Jaber", duration).show();
                lampColour3++;
                lrf.execute();
                intent = new Intent(getActivity().getApplicationContext(), LampColour.class);
                startActivity(intent);
                break;
            //tv remote activity
            case 3: Log.d("ListFragment","TELEVISION REMOTE"); Log.d("ListFragment",Integer.toString(tvR4));
                Toast.makeText(getActivity().getApplicationContext(), "T.V REMOTE, by Mouhamad Jaber", duration).show();
                tvR4++;
                lrf.execute();
                intent = new Intent(getActivity().getApplicationContext(), TvRemote.class);
                startActivity(intent);
                break;
            //Window Blinds activity
            case 4: Log.d("ListFragment","WINDOW BLINDS"); Log.d("ListFragment",Integer.toString(windowBlinds5));
                Toast.makeText(getActivity().getApplicationContext(), "WINDOW CONTROL, by Mouhamad Jaber", duration).show();
                windowBlinds5++;
                lrf.execute();
                intent = new Intent(getActivity().getApplicationContext(), WindowBlinds.class);
                startActivity(intent);
                break;
            //instructions dialog
            case 5: Log.d("ListFragment","INFO");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Instructions");
                //add buttons
                builder.setMessage("LAMP SWITCH - SIMPLE ON and OFF " +
                        "                                        " +
                        "LAMP DIMMABLE - DIM LIGHTS BY SLIDING" +
                        "                                        " +
                        "LAMP COLOUR - SET COLOUR USED" +
                        "                                        " +
                        "WINDOW BLINDS - SLIDE WINDOWS BLINDS" +
                        "                                        " +
                        "TV REMOTE - POWER ON/OFF - ENTER CHANNEL" +

                        " -- Instructions, by Mouhamad Jaber");
                builder.setPositiveButton(OK,new DialogInterface.OnClickListener(){
                    public void onClick (DialogInterface dialog,int id){
                        dialog.dismiss();
                    }

                });

                AlertDialog dialog = builder.create();
                dialog.show();
        }
    }

    /**
     * Async task opens db and stores state of devices
     */
    private class LivingRoomFrag extends AsyncTask<Object, Object, Cursor> {
        //open db and get cursor to store the values into variables
        @Override
        protected Cursor doInBackground(Object... params) {
            //open db
            dbHelper = new DatabaseHelperLivingRoom(getActivity());
            database = dbHelper.getWritableDatabase();
            //get the cursor from mostused table
            Cursor SelectCursor = dbHelper.mostUsedSelect();
            //grab cursor info to variables
            while (SelectCursor.moveToNext()) {
                lampS = SelectCursor.getInt(0);
                Log.d("Lamp Switch", Integer.toString(lampS));
                lampDim = SelectCursor.getInt(1);
                Log.d("Lamp Dim", Integer.toString(lampDim));
                lampColour = SelectCursor.getInt(2);
                Log.d("Lamp Colour", Integer.toString(lampColour));
                tvR = SelectCursor.getInt(3);
                Log.d("Television", Integer.toString(tvR));
                windowBlinds = SelectCursor.getInt(4);
                Log.d("Window Blinds", Integer.toString(windowBlinds));
            }
            //check if the database is open and update the database with updated values
            if(database.isOpen()){
                Log.i("Fragment","Db is OPEN");
                //dbHelper.mostUsed(database,lampDim2,lampS1,lampColour3,tvR4,windowBlinds5);
                dbHelper.mostUsed(database,lampDim+lampDim2,lampS+lampS1,lampColour+lampColour3,tvR+tvR4,windowBlinds+windowBlinds5);
                Log.i("Fragment","mostUsed is Updated");
            }
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
