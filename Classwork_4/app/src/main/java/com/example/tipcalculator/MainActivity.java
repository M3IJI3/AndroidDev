package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements
        TextView.OnEditorActionListener, View.OnClickListener {
    private String billAmountString = "";
    private float tipPercent = .15f;

    private EditText editTextBillAmount;
    private TextView txtViewPercent;
    private Button btnUp;
    private Button btnDown;
    private TextView txtViewTip;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference to the UI controls
        editTextBillAmount = findViewById(R.id.editTxtbillAmount);
        txtViewPercent = findViewById(R.id.txtViewPercent);
        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        txtViewTip = findViewById(R.id.txtViewTip);
        txtTotal = findViewById(R.id.txtViewTotal);

        // set listeners
        editTextBillAmount.setOnEditorActionListener(this);
        btnDown.setOnClickListener(this);
        btnUp.setOnClickListener(this);
    }

    public void calculateAndDisplay()
    {
        billAmountString = editTextBillAmount.getText().toString();
        float billAmount;

        if(billAmountString.equals(""))
        {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }

        // calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // display
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        txtViewTip.setText(currency.format(tipAmount));
        txtTotal.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        txtViewPercent.setText(percent.format(tipPercent));

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnDown)
        {
            tipPercent -= 0.01f;
            if(tipPercent < 0)
            {
                tipPercent = 0;
            }
            calculateAndDisplay();
        }
        else if(view.getId() == R.id.btnUp)
        {
            tipPercent += 0.01f;
            calculateAndDisplay();
        }
    }


    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }
}