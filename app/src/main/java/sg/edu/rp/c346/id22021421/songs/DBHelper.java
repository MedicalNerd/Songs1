package sg.edu.rp.c346.id22021421.songs;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;



public class DBHelper extends SQLiteOpenHelper {
    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "song.db";
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG = "title";
    private static final String COLUMN_SINGERS = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_STAR = "star";



    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE + " INTEGER,"
                + COLUMN_SINGERS + " TEXT"
                + COLUMN_STAR + "INTEGER"
                + COLUMN_SONG + "TEXT)";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);
    }
    public void insertSongs(String song, Integer date,String name,Integer stars){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the name as value
        values.put(COLUMN_SINGERS, name);
        // Store the column name as key and the date as value
        values.put(COLUMN_DATE, date);
        // Store the column name as key and the stars as value
        values.put(COLUMN_STAR, stars);
        // Store the column name as key and the song as value
        values.put(COLUMN_SINGERS,song);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_SONG, null, values);
        // Close the database connection
        db.close();
    }
    @SuppressLint("Range")
    public ArrayList<String> getSongContent() {
        ArrayList<String> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_SINGERS, COLUMN_DATE, COLUMN_SONG, COLUMN_STAR};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                songs.add(cursor.getString(cursor.getColumnIndex(COLUMN_SINGERS)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> get_Songs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_SONG, COLUMN_DATE, COLUMN_STAR,COLUMN_SINGERS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String song = cursor.getString(2);
                int date = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song obj = new Song(id,name, song, date,star);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
}
