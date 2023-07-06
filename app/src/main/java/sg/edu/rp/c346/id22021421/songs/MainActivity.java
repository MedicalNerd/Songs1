package sg.edu.rp.c346.id22021421.songs;

import androidx.appcompat.app.AppCompatActivity;

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

    ListView list;
    ArrayAdapter<Song> adapter;
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
        list = findViewById(R.id.lv);
        al = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        list.setAdapter(adapter);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                int date = Integer.parseInt(etDate.getText().toString());

                String song = etSong.getText().toString();
                String name = etName.getText().toString();
                // Get the selected RadioButton's ID from the RadioGroup
                int selectedRadioButtonId = rgStar.getCheckedRadioButtonId();

                // Get the corresponding RadioButton based on its ID
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                // Get the text from the selected RadioButton
                String selectedRadioButtonText = selectedRadioButton.getText().toString();

                // Convert the text to an integer value
                int star = Integer.parseInt(selectedRadioButtonText);
                Song newSong = new Song(al.size(),name ,song, date,star);
                al.clear();
                al.add(newSong);
                ;
            }
        });
        btnGetSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getSongContent();
                al.addAll(db.get_Songs());
                adapter.notifyDataSetChanged();
                db.close();


                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }


            }
        });
    }

}