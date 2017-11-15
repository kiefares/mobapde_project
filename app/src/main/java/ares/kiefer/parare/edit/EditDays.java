package ares.kiefer.parare.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import ares.kiefer.parare.R;

public class EditDays extends AppCompatActivity{

    CheckBox cbxMonday;
    CheckBox cbxTuesday;
    CheckBox cbxWednesday;
    CheckBox cbxThursday;
    CheckBox cbxFriday;
    CheckBox cbxSaturday;
    CheckBox cbxSunday;
    Button btnDone;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.edit_days_screen);

        int[] currChosenDays = getIntent().getIntArrayExtra("days_values");

        btnDone = (Button) findViewById(R.id.btn_done_days);
        cbxMonday = (CheckBox) findViewById(R.id.cbx_monday);
        cbxTuesday = (CheckBox) findViewById(R.id.cbx_tuesday);
        cbxWednesday = (CheckBox) findViewById(R.id.cbx_wednesday);
        cbxThursday = (CheckBox) findViewById(R.id.cbx_thursday);
        cbxFriday = (CheckBox) findViewById(R.id.cbx_friday);
        cbxSaturday = (CheckBox) findViewById(R.id.cbx_saturday);
        cbxSunday = (CheckBox) findViewById(R.id.cbx_sunday);

        for(int i = 0; i < 7; i++){
            switch(i){
                case(0):
                    if(currChosenDays[i] == 1)
                        cbxMonday.setChecked(true);
                    break;
                case(1):
                    if(currChosenDays[i] == 1)
                        cbxTuesday.setChecked(true);
                    break;
                case(2):
                    if(currChosenDays[i] == 1)
                        cbxWednesday.setChecked(true);
                    break;
                case(3):
                    if(currChosenDays[i] == 1)
                        cbxThursday.setChecked(true);
                    break;
                case(4):
                    if(currChosenDays[i] == 1)
                        cbxFriday.setChecked(true);
                    break;
                case(5):
                    if(currChosenDays[i] == 1)
                        cbxSaturday.setChecked(true);
                    break;
                case(6):
                    if(currChosenDays[i] == 1)
                        cbxSunday.setChecked(true);
                    break;
            }
        }

        btnDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int[] days = new int[7];
                for(int i = 0; i < 7; i++)
                    days[i] = 0;
                if(cbxMonday.isChecked())
                    days[0] = 1;
                if(cbxTuesday.isChecked())
                    days[1] = 1;
                if(cbxWednesday.isChecked())
                    days[2] = 1;
                if(cbxThursday.isChecked())
                    days[3] = 1;
                if(cbxFriday.isChecked())
                    days[4] = 1;
                if(cbxSaturday.isChecked())
                    days[5] = 1;
                if(cbxSunday.isChecked())
                    days[6] = 1;

                Intent data = getIntent();
                data.putExtra("days", days);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
