package ares.kiefer.parare.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import ares.kiefer.parare.R;

public class EditTimeEnd extends AppCompatActivity{

    TimePicker timePicker;
    Button btnDone;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.time_input_screen);

        btnDone = (Button) findViewById(R.id.time_done);
        timePicker = (TimePicker) findViewById(R.id.time_picker);

        timePicker.setIs24HourView(true);

        btnDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                Intent data = getIntent();
                data.putExtra("time_end", calendar.getTimeInMillis());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
