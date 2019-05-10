package com.komal.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CancelActivity extends AppCompatActivity {

    TextView returnAmt;
    double value;
    double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        returnAmt = findViewById(R.id.returnAmt);

        Bundle bundle = getIntent().getExtras();
        Total = bundle.getDouble("Total");
        value = Total*0.8;

        String temp = Double.toString(value);

        returnAmt.setText(temp);
    }
}
