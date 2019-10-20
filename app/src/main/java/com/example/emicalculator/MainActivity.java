package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity<cal_EMI> extends AppCompatActivity {

    private TextView result;
    private EditText principle;
    private EditText rate;
    private EditText month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principle =(EditText) findViewById(R.id.principle);
        rate =(EditText) findViewById(R.id.rate);
        month=(EditText) findViewById(R.id.month);
        result =(TextView) findViewById(R.id.result);

    }

    public void calculateEMI(View v){
        String principleStr = principle.getText().toString();
        String rateStr = rate.getText().toString();
        String monthStr = month.getText().toString();

        if (principleStr !=null && !"".equals(principleStr) &&
            rateStr != null && !"".equals(rateStr) &&
            monthStr != null && !"".equals(monthStr))
        {
            double pValue= Double.parseDouble(principleStr);
            double rValue = Double.parseDouble(rateStr);
            double mValue = Double.parseDouble(monthStr);

//            double EMI = (pValue * (rValue/12/100) * (Math.pow(1+ rValue, mValue*12)))/(Math.pow(1+ rValue, mValue*12)-1);
//            result.setText(String.valueOf(EMI));

            double P = cal_p(pValue);
            double R = cal_r(rValue);
            double N = cal_n(mValue);
            double D1 = cal_d1(R, N);
            double D2 = cal_d2(P, R, D1);
            double D3= cal_D3(D1);
            double EMI = cal_EMI(D2, D3);
            String EMILabel = " Your EMI is Rs : " + roundTwoDecimals(EMI);
            result.setText(EMILabel);
        }
    }
    private double cal_p(double pValue) {
        return (double)(pValue);
    }

    private double cal_r(double rValue) {
        return (double) (rValue/12/100);
    }

    private double cal_n(double nValue){
        return (double) (nValue);
    }
    private double cal_d1(double R, double N) {
        return (double) (Math.pow(1+R ,N));
    }

    private double cal_d2(double P, double R, double D1) {
        return (double) (P * R * D1);
    }

    private double cal_D3(double D1) {
        return (double) (D1 - 1);
    }

    private double cal_EMI(double D2, double D3) {
        return (double) (D2/D3);
    }
    double roundTwoDecimals(double a){
        DecimalFormat form_value= new DecimalFormat("#.##");
        return Double.valueOf(form_value.format(a));
    }



}
