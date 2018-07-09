package com.mobileapps.training.week2daily4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class EMICalculator extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "EMICalculator";

    TextView tvSeekBar, tvAmount, tvEmi;
    SeekBar seekBar;
    double selectedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emicalculator);

        Log.d(TAG, "onCreate: SeekBar started");

        tvSeekBar=findViewById(R.id.tvSeekBar);
        seekBar=findViewById(R.id.seekBar);
        tvAmount =  findViewById(R.id.tvAmount);
        tvEmi =  findViewById(R.id.tvEmi);
        selectedValue = 0;

        seekBar.setMax(10000);
        seekBar.setProgress(1000);
        seekBar.setOnSeekBarChangeListener(this);
        }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        selectedValue = (double)progress;
        tvAmount.setText("Amount selected: " + selectedValue);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void onBtnEmiClicked(View view) {
        if(selectedValue>0){
            tvEmi.setText("EMI: " + calculateEmi());
        }else {
            tvEmi.setText("Could not calculate EMI");
            Toast.makeText(getApplicationContext(),"Select a valid amount", Toast.LENGTH_LONG).show();
        }
    }

    private Double calculateEmi() {
        double R = (double)12 / (double)(12*100);
        double P = selectedValue;
        Log.d(TAG, "calculateEmi: P: " + P + " R: " + R );
        double first = (P*R*(Math.pow((1+R), 24)));
        double second = (Math.pow((1+R),(24-1)));
        double val =  first / second ;
        Log.d(TAG, "calculateEmi: VAL: " + first +" "+ second + " ="+ val);
        return val;
    }
}
