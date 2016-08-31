package com.example.dell.woof.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.interfaces.CalendarListener;
import com.example.dell.woof.views.CustomCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by praful on 11/8/16.
 */
public class CalenderActivity extends AppCompatActivity {
    CustomCalendarView calendarView;
    TextView selectedDate, selectedDay;
    Locale locale;
    String selectedMonth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        selectedDate = (TextView) findViewById(R.id.selected_date);
        selectedDay = (TextView) findViewById(R.id.selected_day);
        //Initialize CustomCalendarView from layout
        calendarView = (CustomCalendarView) findViewById(R.id.calender_view);
        //Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        //Show monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(true);
        //call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);
        selectedDate.setText(currentCalendar.get(Calendar.DATE) + " ");
        String weekDay;
        locale = CalenderActivity.this.getResources().getConfiguration().locale;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", locale);
        weekDay = dayFormat.format(currentCalendar.getTime());
        selectedDay.setText(weekDay);
        //Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", locale);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd", locale);
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", locale);
                selectedMonth = monthFormat.format(date);
                selectedDate.setText(dateFormat.format(date));
                selectedDay.setText(dayFormat.format(date));

            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
            }
        });

        findViewById(R.id.take_a_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(CalenderActivity.this);
                View infoDialogView = inflater.inflate(R.layout.info_dialog, null);
                ((TextView) infoDialogView.findViewById(R.id.tvMessage)).setText("Request sent to "+getIntent().getExtras().getString("doctor"));
                ((TextView) infoDialogView.findViewById(R.id.tvDate)).setText(selectedDate.getText() + " " + selectedDay.getText()+ " 2016");
                AlertDialog alertDialog = new AlertDialog.Builder(CalenderActivity.this).create();
                alertDialog.setView(infoDialogView);
                infoDialogView.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CalenderActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialog.show();
            }
        });

//        //Setting custom font
//        final Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Arch_Rival_Bold.ttf");
//        if (null != typeface) {
//            calendarView.setCustomTypeface(typeface);
//            calendarView.refreshCalendar(currentCalendar);
//        }
    }

}
