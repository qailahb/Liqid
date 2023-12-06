package com.example.liqid20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  {

    DataBaseHelper mydB;
    private static final String[] lists = new String[]{ "QFLOW-VI-LOT", "QFLOW-VI-LOT1", "QFLOW-VI-LOT2", "QFLOW-VI-LOT3", "QFLOW-VI-LOT4", "Add New List" };

    ImageButton buttonSave;
    EditText etSpeed, etTravel, etWait, valueForce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydB = new DataBaseHelper(this);

        AppCompatAutoCompleteTextView saveSelect = (AppCompatAutoCompleteTextView) findViewById(R.id.listSaveSelect);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, lists);
        saveSelect.setThreshold(1);
        saveSelect.setAdapter(adapter);

        AutoCompleteTextView viewSelect = findViewById(R.id.listViewSelect);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, lists);
        viewSelect.setAdapter(adapter2);

        // Initialises SeekBars and EditTexts
        SeekBar sbSpeed = findViewById(R.id.seekBarSpeed);
        SeekBar sbTravel = findViewById(R.id.seekBarTravel);
        SeekBar sbWait = findViewById(R.id.seekBarWait);

        EditText etSpeed = findViewById(R.id.Speed);
        EditText etTravel = findViewById(R.id.Travel);
        EditText etWait = findViewById(R.id.Wait);

        // Initialises button
        Button buttonRun = findViewById(R.id.buttonRun);

        EditText editText = findViewById(R.id.valueForce);

        // Links SeekBar and EditText pairs
        linkSeekBarAndEditText(sbSpeed, etSpeed);
        linkSeekBarAndEditText(sbTravel, etTravel);
        linkSeekBarAndEditText(sbWait, etWait);

        buttonRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to set ESP32 output pin high/low to send data and start force calculation
                editText.setText(getString(R.string.mockForce));
            }
        });

         /*
         buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Code to set ESP32 output pin high/low to send data and start force calculation
                editText.setText(getString(R.string.mockForce));
            }
        });
        */

    }

    // SeekBarChangeListener
    private void linkSeekBarAndEditText(SeekBar sb, EditText et) {
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                et.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Action for when SeekBar tracking starts
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Action for when SeekBar tracking stops
            }
        });

        // TextWatcher for EditText to update based on scrollbar values
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int j, int k) {
                // Do something before text changes
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int j, int k) {
                // Do something when text changes
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int progress = Integer.parseInt(editable.toString());
                    // Sets up the progress of the SeekBar
                    sb.setProgress(progress);
                }
                catch (NumberFormatException e) {
                    // Case for when text can't be converted to an integer
                }
            }
        });
    }

    public void addData() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = mydB.insertData(etSpeed.getText().toString(),
                        etTravel.getText().toString(),
                        etWait.getText().toString(), valueForce.getText().toString());
                if (isInserted)
                    Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Error, could not save", Toast.LENGTH_LONG).show();
            }
        });
    }
}
