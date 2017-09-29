package com.example.maxluzon.timecalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mInitialDate;
    private EditText mEndDate;

    private DatePickerDialog mInitialDatePicker;
    private DatePickerDialog mEndDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInitialDate = (EditText) findViewById(R.id.initial_date);
        mInitialDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calendar Dialog
            }
        });

        mEndDate = (EditText) findViewById(R.id.end_date);
        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calendar Dialog
            }
        });

        findViewById(R.id.today_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Calendar c = java.util.Calendar.getInstance();
                mEndDate.setText(c.get(java.util.Calendar.DAY_OF_MONTH) +"/" +
                                 c.get(java.util.Calendar.MONTH)+"/"+
                                 c.get(java.util.Calendar.YEAR));

                if(!TextUtils.isEmpty(mEndDate.getError()))
                    mEndDate.setError(null);
            }
        });


        findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mInitialDate.getText())){
                    mInitialDate.setError(getString(R.string.initial_date_required));
                }
                if(TextUtils.isEmpty(mEndDate.getText())){
                    mEndDate.setError(getString(R.string.end_date_required));
                }
            }
        });



    }
}
