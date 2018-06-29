package com.mobileapps.training.daily4calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String numberOne;
    private String numberTwo;
    private String operand;
    private String result;
    private Calculator calculator;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberOne = "0";
        numberTwo = "0";
        operand = "";
        calculator = new Calculator();
        textView = findViewById(R.id.screen);
        textView.setText("0");
    }

    public void onNumberClicked(View view) {
        Button btnClicked = findViewById(view.getId());
        Log.d(TAG, "onNumberClicked: Number clicked is " + btnClicked.getText());
        if(operand==null || operand.equals("")){
            //Number one
            Log.d(TAG, "Concat to number one");
            numberOne+=btnClicked.getText().toString();
            calculator.setNumberOne(numberOne);
            showOnScreen(calculator.toString());
        }else{
            Log.d(TAG, "Concat to number two ");
            numberTwo+=btnClicked.getText().toString();
            calculator.setNumberTwo(numberTwo);
            showOnScreen(calculator.toString());
        }
    }

    public void onOperandClicked(View view){
        Button btnClicked = findViewById(view.getId());
        Log.d(TAG, "onOperandClicked: Operand clicked is " + btnClicked.getText());
        operand=btnClicked.getText().toString();
        calculator.setOperand(operand);
        showOnScreen(calculator.toString());
    }

    private void showOnScreen(String val){
        textView = findViewById(R.id.screen);
        textView.setText(val);
    }

    public void onClearSelected(View view){
        numberOne = "0";
        numberTwo = "0";
        calculator = new Calculator();
        operand = "";
        result="";
        showOnScreen(calculator.toString());
    }

    public void onResultClicked(View view) {
        result=calculator.getResult();
        showOnScreen(result);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putString("screenValue", textView.getText().toString());
        outState.putString("numberOne", numberOne);
        outState.putString("numberTwo",numberTwo);
        outState.putString("operand",operand);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
        numberOne=savedInstanceState.getString("numberOne");
        numberTwo=savedInstanceState.getString("numberTwo");
        operand=savedInstanceState.getString("operand");
        calculator.setNumberOne(savedInstanceState.getString("numberOne"));
        calculator.setNumberTwo(savedInstanceState.getString("numberTwo"));
        calculator.setOperand(savedInstanceState.getString("operand"));
        showOnScreen(savedInstanceState.getString("screenValue"));
    }
}
