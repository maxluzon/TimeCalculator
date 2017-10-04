package com.example.maxluzon.timecalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mInitialDate;
    private TextView mEndDate;
    SimpleDateFormat mDateFormat;

    private Calendar mInitialDateCalendar;
    private Calendar mEndDateCalendar;

    private DatePickerDialog mInitialDatePicker;
    private DatePickerDialog mEndDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        mInitialDateCalendar = Calendar.getInstance();
        mEndDateCalendar = Calendar.getInstance();

        mInitialDate = (TextView)findViewById(R.id.initial_date_display);
        mEndDate = (TextView)findViewById(R.id.end_date_display);

        mInitialDatePicker = new DatePickerDialog(this, mInitialDateListener,
                mInitialDateCalendar.get(Calendar.YEAR),
                mInitialDateCalendar.get(Calendar.MONTH),
                mInitialDateCalendar.get(Calendar.DATE));

        mEndDatePicker = new DatePickerDialog(this, mEndDateListener,
                mEndDateCalendar.get(Calendar.YEAR),
                mEndDateCalendar.get(Calendar.MONTH),
                mEndDateCalendar.get(Calendar.DATE));

        findViewById(R.id.pick_initial_date_button).setOnClickListener(this);
        findViewById(R.id.pick_end_date_button).setOnClickListener(this);
        findViewById(R.id.today_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pick_initial_date_button:
                mInitialDatePicker.show();
                break;
            case R.id.pick_end_date_button:
                mEndDatePicker.show();
                break;
            case R.id.today_button:
                Calendar c = Calendar.getInstance();
                mEndDateCalendar.set(Calendar.YEAR, c.get(Calendar.YEAR));
                mEndDateCalendar.set(Calendar.MONTH, c.get(Calendar.MONTH));
                mEndDateCalendar.set(Calendar.DATE, c.get(Calendar.DATE));
                mEndDate.setText(mDateFormat.format(mEndDateCalendar.getTime()));
                calculateElapsedTime();
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener mInitialDateListener
            =   new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker datepicker, int i, int i1, int i2){
            mInitialDateCalendar.set(Calendar.YEAR, i);
            mInitialDateCalendar.set(Calendar.MONTH, i1);
            mInitialDateCalendar.set(Calendar.DATE, i2);
            mInitialDate.setText(mDateFormat.format(mInitialDateCalendar.getTime()));
            calculateElapsedTime();
        }
    };

    private DatePickerDialog.OnDateSetListener mEndDateListener
            =   new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker datepicker, int i, int i1, int i2){
            mEndDateCalendar.set(Calendar.YEAR, i);
            mEndDateCalendar.set(Calendar.MONTH, i1);
            mEndDateCalendar.set(Calendar.DATE, i2);
            mEndDate.setText(mDateFormat.format(mEndDateCalendar.getTime()));
            calculateElapsedTime();
        }
    };

    private void calculateElapsedTime(){
        if(mInitialDateCalendar.after(mEndDateCalendar)){
            Toast.makeText(this, "The EndDate must be after the Initial Date", Toast.LENGTH_SHORT).show();
        }else{

            //-----Total Elapsed Days
            long diff = mEndDateCalendar.getTimeInMillis() - mInitialDateCalendar.getTimeInMillis();
            long totalElapsedDays = diff/(1000*60*60*24);

            //-----Elapsed years
            int elapsedYears = mEndDateCalendar.get(Calendar.YEAR) - mInitialDateCalendar.get(Calendar.YEAR);
            if(mInitialDateCalendar.get(Calendar.MONTH) > mEndDateCalendar.get(Calendar.MONTH) ||
                    (mInitialDateCalendar.get(Calendar.MONTH) == mEndDateCalendar.get(Calendar.MONTH)
                            && mInitialDateCalendar.get(Calendar.DAY_OF_MONTH) > mEndDateCalendar.get(Calendar.DAY_OF_MONTH)))
                elapsedYears--;

            int elapsedMonths = mEndDateCalendar.get(Calendar.MONTH) - mInitialDateCalendar.get(Calendar.MONTH);
            int elapsedDays = mEndDateCalendar.get(Calendar.YEAR) - mInitialDateCalendar.get(Calendar.YEAR);

            StringBuilder sb = new StringBuilder();
            sb.append(elapsedYears);
            sb.append((elapsedDays>1?" Años, ":"Año, "));
            sb.append(elapsedMonths);
            sb.append((elapsedMonths>1?" meses, ":" mes, "));
            sb.append(elapsedDays);
            sb.append((elapsedDays>1?" Dias": " Dia"));
            ((TextView)findViewById(R.id.time_elapsed_result)).setText(sb.toString());

            ((TextView)findViewById(R.id.days_elapsed_result)).setText(String.valueOf(totalElapsedDays));
        }
    }
}
