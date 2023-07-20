package sg.edu.rp.c346.id22021421.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetSongs;
    EditText etSong , etDate , etName;
    RadioGroup rgStar;
    RadioButton rb1,rb2,rb3,rb4,rb5;



    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetSongs = findViewById(R.id.btnGetSongs);
        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDates);
        etSong = findViewById(R.id.etSong);
        rgStar = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        al = new ArrayList<Song>();


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                int date = Integer.parseInt(etDate.getText().toString());

                String song = etSong.getText().toString();
                String name = etName.getText().toString();

                int selectedRadioButtonId = rgStar.getCheckedRadioButtonId();


                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);


                String selectedRadioButtonText = selectedRadioButton.getText().toString();


                int star = Integer.parseInt(selectedRadioButtonText);
                long insert_song = db.insertSongs(song,date,name,star);
                al.clear();
                al.addAll(db.get_Songs());


            }
        });
        btnGetSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);


                // Pass the entire list of songs to the ListActivity
                intent.putExtra("SONG_LIST", al);


                // Start the ListActivity
                startActivity(intent);





            }
        });
    }

}