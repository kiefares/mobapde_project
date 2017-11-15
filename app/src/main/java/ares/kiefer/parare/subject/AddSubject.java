package ares.kiefer.parare.subject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

import ares.kiefer.parare.R;
import ares.kiefer.parare.data.Subject;
import ares.kiefer.parare.database.DatabaseHelper;
import ares.kiefer.parare.edit.EditDateUntil;
import ares.kiefer.parare.edit.EditDays;
import ares.kiefer.parare.edit.EditProfessor;
import ares.kiefer.parare.edit.EditSection;
import ares.kiefer.parare.edit.EditTimeEnd;
import ares.kiefer.parare.edit.EditTimeStart;

public class AddSubject extends AppCompatActivity{

    final static int REQUEST_CODE_PROF = 0;
    final static int REQUEST_CODE_SECTION = 1;
    final static int REQUEST_CODE_TIME_START = 2;
    final static int REQUEST_CODE_TIME_END = 3;
    final static int REQUEST_CODE_DAYS = 4;
    final static int REQUEST_CODE_UNTIL_DATE = 5;
    private long currentID;
    Button btnDone;
    ImageButton editProfessor;
    ImageButton editSection;
    ImageButton editTimeStart;
    ImageButton editTimeEnd;
    ImageButton editDays;
    Button btnUntilDate;
    EditText subjName;
    DatabaseHelper databaseHelper;
    TextView tvProf;
    TextView tvSection;
    TextView tvTimeStart;
    TextView tvTimeEnd;
    TextView tvDateUntil;
    TextView tvDays;

    private String subjectName;
    private String professorName;
    private String section;
    private long timeStart;
    private long timeEnd;
    private long timeUntil;
    private int[] days;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.add_subject_screen);

        tvProf = (TextView) findViewById(R.id.tv_professor_name);
        tvSection = (TextView) findViewById(R.id.tv_section_name);
        tvTimeStart = (TextView) findViewById(R.id.tv_start_time);
        tvTimeEnd = (TextView) findViewById(R.id.tv_end_time);
        tvDateUntil = (TextView) findViewById(R.id.tv_until_date);
        tvDays = (TextView) findViewById(R.id.tv_days_subj);

        days = new int[7];

        Bundle extras = getIntent().getExtras();
        currentID = extras.getLong("id");

        timeUntil = Calendar.getInstance().getTimeInMillis();

        btnDone = (Button) findViewById(R.id.btnDone);
        editProfessor = (ImageButton) findViewById(R.id.edit_button_professor);
        editProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), EditProfessor.class), REQUEST_CODE_PROF);
            }
        });
        editSection = (ImageButton) findViewById(R.id.edit_button_section);
        editSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), EditSection.class), REQUEST_CODE_SECTION);
            }
        });
        editTimeStart = (ImageButton) findViewById(R.id.edit_button_time_start);
        editTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), EditTimeStart.class), REQUEST_CODE_TIME_START);
            }
        });
        editTimeEnd = (ImageButton) findViewById(R.id.edit_button_time_end);
        editTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), EditTimeEnd.class), REQUEST_CODE_TIME_END);
            }
        });
        editDays = (ImageButton) findViewById(R.id.edit_button_days);
        editDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditDays.class);
                intent.putExtra("days_values", days);
                startActivityForResult(intent, REQUEST_CODE_DAYS);
            }
        });
        btnUntilDate = (Button) findViewById(R.id.btn_until_change);
        btnUntilDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditDateUntil.class);
                intent.putExtra("until_date_value", timeUntil);
                startActivityForResult(intent, REQUEST_CODE_UNTIL_DATE);
            }
        });
        subjName = (EditText) findViewById(R.id.et_subject_name);
        btnDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Subject subject = new Subject();
                subject.setSubjectName(subjName.getText().toString());
                subject.setProfessorName(professorName);
                subject.setSection(section);
                subject.setTimeStart(timeStart);
                subject.setTimeEnd(timeEnd);
                subject.setUntilDate(timeUntil);
                subject.setUserID(getIntent().getLongExtra("user_id", -1));
                for(int i = 0; i < 7; i++)
                    subject.setClassDay(i, days[i]);
                databaseHelper = DatabaseHelper.getInstance(getApplicationContext());
                databaseHelper.addSubject(subject);
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case (REQUEST_CODE_PROF):
                    Bundle bundle = data.getExtras();
                    professorName = bundle.getString("prof_name");
                    tvProf.setText(professorName);
                    break;
                case (REQUEST_CODE_SECTION):
                    section = data.getStringExtra("section");
                    tvSection.setText(section);
                    break;
                case (REQUEST_CODE_TIME_START):
                    timeStart = data.getLongExtra("time_start", -1);
                    String time = "";
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(timeStart);
                    if(calendar.get(Calendar.HOUR_OF_DAY) < 10)
                        time = time.concat("0" + calendar.get(Calendar.HOUR_OF_DAY) + ":");
                    else
                        time = time.concat(calendar.get(Calendar.HOUR_OF_DAY) + ":");
                    if(calendar.get(Calendar.MINUTE) < 10)
                        time = time.concat("0" + calendar.get(Calendar.MINUTE));
                    else
                        time = time.concat(calendar.get(Calendar.MINUTE) + "");
                    tvTimeStart.setText(time);
                    break;
                case (REQUEST_CODE_TIME_END):
                    timeEnd = data.getLongExtra("time_end", -1);
                    String timeFrame = "";
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTimeInMillis(timeEnd);
                    if(calendar2.get(Calendar.HOUR_OF_DAY) < 10)
                        timeFrame = timeFrame.concat("0" + calendar2.get(Calendar.HOUR_OF_DAY) + ":");
                    else
                        timeFrame = timeFrame.concat(calendar2.get(Calendar.HOUR_OF_DAY) + ":");
                    if(calendar2.get(Calendar.MINUTE) < 10)
                        timeFrame = timeFrame.concat("0" + calendar2.get(Calendar.MINUTE));
                    else
                        timeFrame = timeFrame.concat(calendar2.get(Calendar.MINUTE) + "");
                    tvTimeEnd.setText(timeFrame);
                    break;
                case (REQUEST_CODE_DAYS):
                    days = data.getIntArrayExtra("days");
                    String days_string = "";
                    int day_counter = 0;
                    for(int i = 0; i < 7; i++){
                        if(day_counter != 0)
                            days_string = days_string.concat(" ");
                        if(days[i] == 1){
                            switch(i){
                                case 0: days_string = days_string.concat("M");
                                    break;
                                case 1: days_string = days_string.concat("T");
                                    break;
                                case 2: days_string = days_string.concat("W");
                                    break;
                                case 3: days_string = days_string.concat("H");
                                    break;
                                case 4: days_string = days_string.concat("F");
                                    break;
                                case 5: days_string = days_string.concat("S");
                                    break;
                                case 6: days_string = days_string.concat("S");
                                    break;
                            }
                            day_counter++;
                        }
                    }
                    if(day_counter == 8)
                        tvDays.setText("Everyday");
                    else
                        tvDays.setText(days_string.replaceAll("\\s+$", ""));
                    break;
                case (REQUEST_CODE_UNTIL_DATE):
                    timeUntil = data.getLongExtra("date", -1);
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTimeInMillis(timeUntil);
                    tvDateUntil.setText(calendar3.get(Calendar.MONTH) + 1 + "-" + calendar3.get(Calendar.DAY_OF_MONTH) + "-" + calendar3.get(Calendar.YEAR));
                    break;
            }
        }
    }
}
