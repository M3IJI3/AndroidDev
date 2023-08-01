package com.example.classwork_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    double costPerTicketToCatalina = 34;
    double costPerTicketToLongBeach = 40;
    int numberOfTicket;
    double totalCost;
    String tripChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText tickets = findViewById(R.id.edTickets);
        Spinner destination = findViewById(R.id.spinnerDes);
        TextView result = findViewById(R.id.txtResult);
        Button compute = findViewById(R.id.btnCost);

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    numberOfTicket = Integer.parseInt(tickets.getText().toString());
                }
                catch (NumberFormatException e){
                    result.setText("Please enter the number rof tickets you need!");
                }

                NumberFormat currency = NumberFormat.getCurrencyInstance();

                tripChoice = destination.getSelectedItem().toString();

                if(destination.getSelectedItemPosition() == 0)
                    totalCost = costPerTicketToCatalina * numberOfTicket;
                else
                    totalCost = costPerTicketToLongBeach * numberOfTicket;

                result.setText("One Way Trip " + tripChoice + " for " + numberOfTicket + " passengers: " +
                        currency.format(totalCost));
            }
        });
    }
}