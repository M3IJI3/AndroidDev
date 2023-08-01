package com.example.project;

import java.text.DecimalFormat;

public class AppFunctions implements AppFunctionsInterface{
    public AppFunctions(){}
    @Override
    public String calcBMI(String weightText, String heightText) {
        float weight = Float.parseFloat(weightText);
        float height = Float.parseFloat(heightText) / 100;

        float BMI = weight / (height * height);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String result = decimalFormat.format(BMI);

        return result;
    }

    @Override
    public String getBMIResult(String BMIText) {
        
        String result;
        float BMI = Float.parseFloat(BMIText);

        if (BMI <= 18.4)
        {
            result = "Underweight";
        } else if (BMI > 18.5 && BMI <= 24.9) {
            result = "Normal";
        } else if (BMI > 25.0 && BMI <= 29.9) {
            result = "Overweight";
        } else {
            result = "Obese";
        }

        return result;
    }
}
