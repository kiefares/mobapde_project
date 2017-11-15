package ares.kiefer.parare.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

import ares.kiefer.parare.R;

public class EditDateUntil extends AppCompatActivity{

    CalendarView calendarView;
    Button btnDone;
    Calendar calendar;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.add_task_calendar);

        btnDone = (Button) findViewById(R.id.btnDone);
        calendarView = (CalendarView) findViewById(R.id.cal_view);
        calendarView.setDate(getIntent().getLongExtra("until_date_value", Calendar.getInstance().getTimeInMillis()));

        calendar = Calendar.getInstance();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent data = getIntent();
                data.putExtra("date", calendar.getTimeInMillis());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
