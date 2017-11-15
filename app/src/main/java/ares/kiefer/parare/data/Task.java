package ares.kiefer.parare.data;

/**
 * Created by Kiefer on 11/12/2017.
 */

public class Task {

    public static final String TABLE_NAME = "user_task";
    public static final String COLUMN_ID = "task_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_SUBJ_ID = "subj_id";
    public static final String COLUMN_NOTE = "task_note";
    public static final String COLUMN_DATE = "task_date";

    private long task_id;
    private long user_id;
    private long subj_id;
    private long date;
    public String task_note;

    public Task(){
    }

    public void setTaskID(long id){
        task_id = id;
    }

    public long getTaskID(){
        return task_id;
    }

    public void setUserID(long id){
        user_id = id;
    }

    public long getUserID(){
        return user_id;
    }

    public void setSubjID(long id){
        subj_id = id;
    }

    public long getSubjID(){
        return subj_id;
    }

    public void setDate(long date){
        this.date = date;
    }

    public long getDate(){
        return date;
    }

    public void setTaskNote(String note){
        task_note = note;
    }

    public String getTaskNote(){
        return task_note;
    }
}
