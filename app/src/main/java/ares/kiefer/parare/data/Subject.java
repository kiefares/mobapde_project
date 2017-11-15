package ares.kiefer.parare.data;

/**
 * Created by Kiefer on 11/10/2017.
 */

public class Subject {

    public static final String TABLE_NAME = "app_subjects";
    public static final String COLUMN_ID = "subj_id";
    public static final String COLUMN_USER_ID = "subj_user_id";
    public static final String COLUMN_NAME = "subj_name";
    public static final String COLUMN_PROF = "prof_name";
    public static final String COLUMN_SECTION = "subj_section";
    public static final String COLUMN_TIME_START = "subj_time_start";
    public static final String COLUMN_TIME_END = "subj_time_end";
    public static final String COLUMN_UNTIL_DATE = "subj_until_date";
    public static final String COLUMN_MONDAY = "subj_monday_class";
    public static final String COLUMN_TUESDAY = "subj_tuesday_class";
    public static final String COLUMN_WEDNESDAY = "subj_wednesday_class";
    public static final String COLUMN_THURSDAY = "subj_thursday_class";
    public static final String COLUMN_FRIDAY = "subj_friday_class";
    public static final String COLUMN_SATURDAY = "subj_saturday_class";
    public static final String COLUMN_SUNDAY = "subj_sunday_class";

    private long id;
    private long user_id;
    private long time_start;
    private long time_end;
    private long until_date;
    private String subject_name;
    private String professor_name;
    private String subject_section;
    private int[] subject_days;

    public Subject(){
        subject_days = new int[7];
    }

    public void setID(long id){
        this.id = id;
    }

    public long getID(){
        return id;
    }

    public void setUserID(long id){
        user_id = id;
    }

    public long getUserID(){
        return user_id;
    }

    public void setTimeStart(long time){
        time_start = time;
    }

    public long getTimeStart(){
        return time_start;
    }

    public void setTimeEnd(long time){
        time_end = time;
    }

    public long getTimeEnd(){
        return time_end;
    }

    public void setUntilDate(long time){
        until_date = time;
    }

    public long getUntilDate(){
        return until_date;
    }

    public void setSubjectName(String name){
        subject_name = name;
    }

    public String getSubjectName(){
        return subject_name;
    }

    public void setProfessorName(String name){
        professor_name = name;
    }

    public String getProfessorName(){
        return professor_name;
    }

    public void setSection(String section){
        subject_section = section;
    }

    public String getSection(){
        return subject_section;
    }

    public void setClassDay(int day, int isClassDay){
        subject_days[day] = isClassDay;
    }

    public int isClassDay(int day){
        return subject_days[day];
    }
}