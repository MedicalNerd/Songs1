package sg.edu.rp.c346.id22021421.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    TextView tvID;
    EditText etName,etSongs,etDates;
    Button btnUpdate, btnDelete,btnCancel;
    Song data;
    RadioGroup rgStars;
    RadioButton rb1,rb2,rb3,rb4,rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);
        tvID = findViewById(R.id.tvID);
        etName = findViewById(R.id.etName);
        etDates = findViewById(R.id.etDates);
        etSongs = findViewById(R.id.etSong);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        rgStars = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");

        // Populate the text fields with the selected Song's data
        tvID.setText("ID: " + String.valueOf(data.getId()));

        etName.setText(data.getname());
        etSongs.setText(data.getSong());
        etDates.setText(String.valueOf(data.getDate()));
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setName(etName.getText().toString());
                int date = Integer.parseInt(etDates.getText().toString());
                data.setDate(date);
                data.setSong(etSongs.getText().toString());
                int selectedRadioButtonId = rgStars.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedRadioButtonText = selectedRadioButton.getText().toString();
                int star = Integer.parseInt(selectedRadioButtonText);
                data.setStar(star);

                dbh.updateSong(data);

                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditActivity.this);
                db.deleteSong(data.getId());

                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int starRating = data.getStar();
        RadioButton radioButton = null;
        switch (starRating) {
            case 1:
                radioButton = findViewById(R.id.rb1);
                break;
            case 2:
                radioButton = findViewById(R.id.rb2);
                break;
            case 3:
                radioButton = findViewById(R.id.rb3);
                break;
            case 4:
                radioButton = findViewById(R.id.rb4);
                break;
            case 5:
                radioButton = findViewById(R.id.rb5);
                break;
        }
        if (radioButton != null) {
            radioButton.setChecked(true);
        }


    }
}
