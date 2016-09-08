//midterm assignment

package com.maggard.burnedcaloriescalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.text.NumberFormat;



public class BurnedCaloriesCalculatorActivity extends AppCompatActivity
        implements OnEditorActionListener{

    //define variables
    private EditText weightEditText;
    private TextView milesLabelTextView;
    private SeekBar seekBar;
    private TextView caloriesLabelTextView;
    private Spinner spinnerFeet;
    private Spinner spinnerInches;
    private TextView bmiTextView;
    private EditText nameEditText;

    //define instance variables
    String weightString;
    double weightAmount;
    double caloriesBurned;
    String milesRan;
    double milesAmount;

    //define shared preferences
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burned_calories_calculator);

        //****************************************************************************
        //Set Reference to Widgets   **************************************************
        //****************************************************************************

        weightEditText = (EditText) findViewById(R.id.weightEditText);
        milesLabelTextView  = (TextView) findViewById(R.id.milesLabelTextView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        caloriesLabelTextView = (TextView) findViewById(R.id.caloriesLabelTextView);
        spinnerFeet = (Spinner) findViewById(R.id.spinnerFeet);
        spinnerInches = (Spinner) findViewById(R.id.spinnerInches);
        bmiTextView = (TextView) findViewById(R.id.bmiTextView);
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        //****************************************************************************
        //SET LISTENERS   *************************************************************
        //****************************************************************************

        weightEditText.setOnEditorActionListener(this);
        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);

        spinnerFeet.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //****************************************************************************
        //SEEK BAR LISTENER ***********************************************************
        //****************************************************************************
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int seekBarProgress = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgress = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                calculateAndDisplay();
            }


        });

    }
    //****************************************************************************
    //SPINNERS   ************************************************************
    //****************************************************************************



    //get shared preferences
    //savedValues = getSharedPreferences("SavedValues",MODE_PRIVATE);

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }




    //****************************************************************************
    //calculate and display    *************************************************
    //****************************************************************************

    private void calculateAndDisplay() {
       milesRan = milesLabelTextView.getText().toString();
        weightString = weightEditText.getText().toString();
        weightAmount = Float.parseFloat(weightString);
        milesAmount = Float.parseFloat(milesRan);

        caloriesBurned = (.75*weightAmount * milesAmount);

        int bmi = (weightAmount *703)/((12* 5+5)*12*5+5));

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        caloriesLabelTextView.setText(currency.format(caloriesBurned));
        bmiTextView.setText(currency.format(bmi));



    }


}
