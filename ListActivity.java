package sg.edu.rp.c346.id22021421.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView lvShow;

    ArrayAdapter<Song> adapterSong;
    ArrayAdapter<String> adapterSpinner;
    ArrayList<Song> songList;
    ArrayList<Song> filteredList;
    boolean runOnresume;
    Spinner spYear;


    Button btnStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        runOnresume = false;
        lvShow = findViewById(R.id.lv);
        btnStars = findViewById(R.id.btn5star);
        spYear = findViewById(R.id.spYear);


        Intent intent = getIntent();
        songList = (ArrayList<Song>) intent.getSerializableExtra("SONG_LIST");


        ArrayList<String> dateList = new ArrayList<>();
        for (Song song : songList) {
            dateList.add(String.valueOf(song.getDate()));
        }
        adapterSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,dateList);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spYear.setAdapter(adapterSpinner);
        // Set up the adapter and display the song list
        adapterSong = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songList);
        lvShow.setAdapter(adapterSong);


        btnStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int starRating = 5;

                filteredList = new ArrayList<>();

                for (Song song : songList) {
                    if (song.getStar() == starRating) {
                        filteredList.add(song);
                    }
                }

                adapterSong.clear();
                adapterSong.addAll(filteredList);
                adapterSong.notifyDataSetChanged();
            }
        });
        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song selectedSong = songList.get(position);
                Intent i = new Intent(ListActivity.this,
                        EditActivity.class);
                i.putExtra("data", selectedSong);
                startActivity(i);
            }
        });

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedYear = parent.getItemAtPosition(position).toString();
                DBHelper dbh = new DBHelper(ListActivity.this);
                ArrayList<Song> filteredSongs = filterSongsByYear(selectedYear);
                adapterSong.clear();
                adapterSong.addAll(filteredSongs);
                adapterSong.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the ListView data when the activity is resumed
        DBHelper db = new DBHelper(ListActivity.this);

        // Clear the existing adapter data and add the new data
        adapterSong.clear();
        adapterSong.addAll(db.get_Songs());

        // Notify the adapter that the data has changed
        adapterSong.notifyDataSetChanged();
    }
    private ArrayList<Song> filterSongsByYear(String selectedYear) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songList) {
            if (String.valueOf(song.getDate()).equals(selectedYear)) {
                filteredSongs.add(song);
            }
        }

        return filteredSongs;
    }




}
