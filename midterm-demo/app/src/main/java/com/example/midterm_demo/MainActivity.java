package com.example.midterm_demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements
        TextView.OnEditorActionListener, View.OnClickListener  {

    private String billAmountString = "";
    private float tipPercent = .10f;

    private EditText editTextAmount;
    private TextView txtPercent;
    private Button btnAdd;
    private Button btnMinus;
    private TextView txtTips;
    private TextView txtTotal;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference of ui interface
        editTextAmount = findViewById(R.id.editTextAmount);
        txtPercent = findViewById(R.id.txtPercent);
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        txtTips = findViewById(R.id.txtTips);
        txtTotal = findViewById(R.id.txtTotal);
        btnSubmit = findViewById(R.id.btnSubmit);

        // set action listener
        editTextAmount.setOnEditorActionListener(this);
        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString("billAmountString", billAmountString);
        outState.putFloat("tipPercent", tipPercent);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);

        if(savedInstanceState != null)
        {
            savedInstanceState.putString("billAmountString", "");
            savedInstanceState.putFloat("tipPercent", .10f);
            editTextAmount.setText(billAmountString);

            calculateAndDisplay();
        }
    }

    public void calculateAndDisplay()
    {
        billAmountString = editTextAmount.getText().toString();
        float billAmount;

        if(billAmountString.equals(""))
        {
            billAmount = 0;
        }
        else
        {
            billAmount = Float.parseFloat(billAmountString);
        }

        float tipAmount = billAmount * tipPercent;
        float total = billAmount + tipAmount;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        txtTips.setText(currency.format(tipAmount));
        txtTotal.setText(currency.format(total));

        NumberFormat percent = NumberFormat.getPercentInstance();
        txtPercent.setText(percent.format(tipPercent));

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnAdd)
        {
            tipPercent += 0.01f;
            calculateAndDisplay();
        }
        else if(view.getId() == R.id.btnMinus)
        {
            tipPercent -= 0.01f;

            if(tipPercent < 0)
                tipPercent = 0;
            calculateAndDisplay();
        }

        else if(view.getId() == R.id.btnSubmit)
        {
//            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("amount", billAmountString);
//            intent.putExtras(bundle);
//            startActivity(intent);

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("amount", txtTotal.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }
}