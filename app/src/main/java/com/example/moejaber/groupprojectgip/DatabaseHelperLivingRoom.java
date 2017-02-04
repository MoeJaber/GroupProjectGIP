package com.example.moejaber.groupprojectgip;

/**
 * Created by MoeJaber on 2016-11-28.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelperLivingRoom extends SQLiteOpenHelper
{
    private SQLiteDatabase database;
    private DatabaseHelperLivingRoom dbHelper;
    Context dbContext;

    public final static String DATABASE_NAME = "rooms.db";
    public final static String ROOM_TABLE = "room";

    public final static int CHANNEL_NUM = 5;

    public final static String KEY_ID= "room_id";

    //open = 1 close = 0
    public final static String TELEVISION = "TelevisionRemote";
    public final static String LAMP = "ONorOFF";
    public final static String BLINDS = "WindowControl";

    public final static String TVUSED = "tvUsed";
    public final static String LAMPONOFF = "lampOnOff";
    public final static String LAMPDIM= "lampDim";
    public final static String LAMPCOLOUR = "lampColour";
    public final static String WINDOWBLINDS = "windowBlinds";

    private final static String DATABASE_CREATE = "create table " +
            ROOM_TABLE + "(" +
            KEY_ID + " integer primary key autoincrement, " +
            BLINDS + " integer," +
            LAMP + " integer," +
            TVUSED + " integer," +
            LAMPONOFF + " integer," +
            LAMPDIM + " integer," +
            LAMPCOLOUR + " integer," +
            WINDOWBLINDS + " integer," +
            TELEVISION + " integer" +
            ");";


    public DatabaseHelperLivingRoom(Context ctx){
        super(ctx,DATABASE_NAME, null,CHANNEL_NUM);
        Log.i("DatabaseHelper", "Calling onCreate");
        this.dbContext = ctx;

    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(DATABASE_CREATE);
        Log.i("ChatDatabaseHelper", "Calling onCreate");

    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ROOM_TABLE);
        onCreate(db);
        Log.i("DatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }

    /**
     *  //database method to add the track of the most frequently pressed activity
     * @param database
     * @param lampDim
     * @param lampOnOff
     * @param lampColour
     * @param tvUsed
     * @param windowBlinds
     */
    public void mostUsed(SQLiteDatabase database, int lampDim, int lampOnOff, int lampColour, int tvUsed, int windowBlinds){

        ContentValues values = new ContentValues();
        values.put(LAMPONOFF,lampOnOff);
        values.put(LAMPDIM,lampDim);
        values.put(LAMPCOLOUR,lampColour);
        values.put(TVUSED,tvUsed);
        values.put(WINDOWBLINDS,windowBlinds);
        database.update(ROOM_TABLE,values,"room_id = (SELECT MAX(room_id) FROM room);",null);
        //database.insert(ROOM_TABLE, null, values);
    }
    //Cursor method to return the values inside the selected query
    public Cursor mostUsedSelect(){
        dbHelper = new DatabaseHelperLivingRoom(dbContext);
        //open db
        database = dbHelper.getWritableDatabase();
       Cursor SelectCursor = database.rawQuery("SELECT lampOnOff, lampDim, lampColour, tvUsed, windowBlinds FROM room WHERE room_id = (SELECT MAX(room_id) FROM room);",null );


        return SelectCursor;
    }

    /**
     * //method to update the lamp state and store it in the database
     * @param database database object to get reference
     * @param lamp integer value of lamp state
     */

    public void lightSwitch(SQLiteDatabase database, int lamp){

        ContentValues values = new ContentValues();
        values.put(LAMP,lamp);

        database.update(ROOM_TABLE,values,"room_id = (SELECT MAX(room_id) FROM room);",null);
        //database.insert(ROOM_TABLE, null, values);
    }
    //select the values of the light switch state and store in cursor
    public Cursor lightSwitchSelect(){
        dbHelper = new DatabaseHelperLivingRoom(dbContext);
        //open db
        database = dbHelper.getWritableDatabase();
        Cursor SelectCursor = database.rawQuery("SELECT ONorOFF FROM room WHERE room_id = (SELECT MAX(room_id) FROM room);",null );


        return SelectCursor;
    }
}
