package com.example.classwork16_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.classwork16_2.databinding.ActivityMainBinding;
import com.example.classwork16_2.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);


        Bundle extra = getIntent().getExtras();
        if(extra == null)
        {
            return;
        }

        String qString = extra.getString("qString");
        binding.textView2.setText(qString);
    }

    public void returnText(View view)
    {
        finish();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        String returnString = binding.editText2.getText().toString();
        data.putExtra("returnData", returnString);
        setResult(RESULT_OK, data);

        super.finish();
    }
}