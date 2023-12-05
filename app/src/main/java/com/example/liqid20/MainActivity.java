package com.example.liqid20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    //public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String[] lists = new String[]{ "QFLOW-VI-LOT", "QFLOW-VI-LOT1", "QFLOW-VI-LOT2", "QFLOW-VI-LOT3", "QFLOW-VI-LOT4" };
    private SeekBar sbSpeed, sbTravel, sbWait;
    private EditText etSpeed, etTravel, etWait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        // Spinner setup
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        */

        AutoCompleteTextView saveSelect = findViewById(R.id.listSaveSelect);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lists);
        saveSelect.setAdapter(adapter);

        AutoCompleteTextView viewSelect = findViewById(R.id.listViewSelect);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lists);
        viewSelect.setAdapter(adapter2);

        // Initialises SeekBars and EditTexts
        sbSpeed = findViewById(R.id.seekBarSpeed);
        sbTravel = findViewById(R.id.seekBarTravel);
        sbWait = findViewById(R.id.seekBarWait);

        etSpeed = findViewById(R.id.Speed);
        etTravel = findViewById(R.id.Travel);
        etWait = findViewById(R.id.Wait);

        // Initialises button
        Button buttonRun = findViewById(R.id.buttonRun);
       // Button buttonSave = findViewById(R.id.buttonSave);

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
         /*buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Code to set ESP32 output pin high/low to send data and start force calculation
                editText.setText(getString(R.string.mockForce));
            }
        }); */

    }
    /*
    // Spinner selection
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), "" +users[position] ,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }
    */
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
                } catch (NumberFormatException e) {
                    // Case for when text can't be converted to an integer
                }
            }
        });
    }

}