package ares.kiefer.parare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ares.kiefer.parare.data.Subject;
import ares.kiefer.parare.data.Task;
import ares.kiefer.parare.data.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SCHEMA = "parare_database";
    private static final int VERSION = 1;
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private DatabaseHelper(Context context){
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String sql = "CREATE TABLE " + User.TABLE_NAME + " ("
                + User.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + User.COLUMN_USERNAME + " STRING, "
                + User.COLUMN_PASSWORD + " STRING"
                + ");";

        database.execSQL(sql);

        sql = "CREATE TABLE " + Subject.TABLE_NAME + " ("
                + Subject.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Subject.COLUMN_USER_ID + " INTEGER, "
                + Subject.COLUMN_NAME + " STRING, "
                + Subject.COLUMN_PROF + " STRING, "
                + Subject.COLUMN_SECTION + " STRING, "
                + Subject.COLUMN_UNTIL_DATE + " BIGINT, "
                + Subject.COLUMN_TIME_START + " BIGINT, "
                + Subject.COLUMN_TIME_END + " BIGINT, "
                + Subject.COLUMN_MONDAY + " TINYINT(1), "
                + Subject.COLUMN_TUESDAY + " TINYINT(1), "
                + Subject.COLUMN_WEDNESDAY + " TINYINT(1), "
                + Subject.COLUMN_THURSDAY + " TINYINT(1), "
                + Subject.COLUMN_FRIDAY + " TINYINT(1), "
                + Subject.COLUMN_SATURDAY + " TINYINT(1), "
                + Subject.COLUMN_SUNDAY + " TINYINT(1)"
                + ");";

        database.execSQL(sql);

        sql = "CREATE TABLE " + Task.TABLE_NAME + " ("
                + Task.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Task.COLUMN_SUBJ_ID + " INTEGER, "
                + Task.COLUMN_USER_ID + " INTEGER, "
                + Task.COLUMN_DATE + " BIGINT, "
                + Task.COLUMN_NOTE + " STRING"
                + ");";

        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + User.TABLE_NAME + ";";
        database.execSQL(sql);

        sql = "DROP TABLE IF EXISTS " + Subject.TABLE_NAME + ";";
        database.execSQL(sql);

        sql = "DROP TABLE IF EXISTS " + Task.TABLE_NAME + ";";
        database.execSQL(sql);

        onCreate(database);
    }

    public long addUser(User user){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COLUMN_USERNAME, user.getUsername());
        contentValues.put(User.COLUMN_PASSWORD, user.getPassword());

        long id = db.insert(User.TABLE_NAME, null, contentValues);

        db.close();
        return id;
    }

    public boolean editUser(User userDetails, int id){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COLUMN_USERNAME, userDetails.getUsername());
        contentValues.put(User.COLUMN_PASSWORD, userDetails.getPassword());

        int rowsAffected = db.update(User.TABLE_NAME,
                contentValues,
                User.COLUMN_ID + "=?",
                new String[]{userDetails.getID()+""});

        db.close();
        return rowsAffected > 0;
    }

    public boolean deleteUser(long id){
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(User.TABLE_NAME,
                User.COLUMN_ID + "=?", new String[]{id+""});
        db.close();
        return rowsAffected > 0;
    }

    public User getUser(long id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(User.TABLE_NAME,
                null,
                User.COLUMN_ID + "=?",
                new String[]{id+""},
                null,
                null,
                null);
        User user = null;
        if(c.moveToFirst()){
            user = new User();
            user.setUsername(c.getString(c.getColumnIndex(User.COLUMN_USERNAME)));
            user.setPassword(c.getString(c.getColumnIndex(User.COLUMN_PASSWORD)));
            user.setID(id);
        }
        c.close();
        db.close();

        return user;
    }

    public long addSubject(Subject subject){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Subject.COLUMN_USER_ID, subject.getUserID());
        contentValues.put(Subject.COLUMN_NAME, subject.getSubjectName());
        contentValues.put(Subject.COLUMN_PROF, subject.getProfessorName());
        contentValues.put(Subject.COLUMN_SECTION, subject.getSection());
        contentValues.put(Subject.COLUMN_TIME_START, subject.getTimeStart());
        contentValues.put(Subject.COLUMN_TIME_END, subject.getTimeEnd());
        contentValues.put(Subject.COLUMN_UNTIL_DATE, subject.getUntilDate());
        contentValues.put(Subject.COLUMN_MONDAY, subject.isClassDay(0));
        contentValues.put(Subject.COLUMN_TUESDAY, subject.isClassDay(1));
        contentValues.put(Subject.COLUMN_WEDNESDAY, subject.isClassDay(2));
        contentValues.put(Subject.COLUMN_THURSDAY, subject.isClassDay(3));
        contentValues.put(Subject.COLUMN_FRIDAY, subject.isClassDay(4));
        contentValues.put(Subject.COLUMN_SATURDAY, subject.isClassDay(5));
        contentValues.put(Subject.COLUMN_SUNDAY, subject.isClassDay(6));

        long id = db.insert(Subject.TABLE_NAME, null, contentValues);

        db.close();
        return id;
    }

    public boolean editSubject(Subject subjectDetails, int id){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Subject.COLUMN_USER_ID, subjectDetails.getUserID());
        contentValues.put(Subject.COLUMN_NAME, subjectDetails.getSubjectName());
        contentValues.put(Subject.COLUMN_PROF, subjectDetails.getProfessorName());
        contentValues.put(Subject.COLUMN_SECTION, subjectDetails.getSection());
        contentValues.put(Subject.COLUMN_TIME_START, subjectDetails.getTimeStart());
        contentValues.put(Subject.COLUMN_TIME_END, subjectDetails.getTimeEnd());
        contentValues.put(Subject.COLUMN_UNTIL_DATE, subjectDetails.getUntilDate());
        contentValues.put(Subject.COLUMN_MONDAY, subjectDetails.isClassDay(0));
        contentValues.put(Subject.COLUMN_TUESDAY, subjectDetails.isClassDay(1));
        contentValues.put(Subject.COLUMN_WEDNESDAY, subjectDetails.isClassDay(2));
        contentValues.put(Subject.COLUMN_THURSDAY, subjectDetails.isClassDay(3));
        contentValues.put(Subject.COLUMN_FRIDAY, subjectDetails.isClassDay(4));
        contentValues.put(Subject.COLUMN_SATURDAY, subjectDetails.isClassDay(5));
        contentValues.put(Subject.COLUMN_SUNDAY, subjectDetails.isClassDay(6));

        int rowsAffected = db.update(Subject.TABLE_NAME,
                contentValues,
                Subject.COLUMN_ID + "=?",
                new String[]{subjectDetails.getID()+""});

        db.close();
        return rowsAffected > 0;
    }

    public boolean deleteSubject(long id){
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(Subject.TABLE_NAME,
                Subject.COLUMN_ID + "=?", new String[]{id+""});
        db.close();
        return rowsAffected > 0;
    }

    public Subject getSubject(long id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Subject.TABLE_NAME,
                null,
                Subject.COLUMN_ID + "=?",
                new String[]{id+""},
                null,
                null,
                null);
        Subject subject = null;
        if(c.moveToFirst()){
            subject = new Subject();
            subject.setSubjectName(c.getString(c.getColumnIndex(Subject.COLUMN_NAME)));
            subject.setUserID(c.getLong(c.getColumnIndex(Subject.COLUMN_USER_ID)));
            subject.setProfessorName(c.getString(c.getColumnIndex(Subject.COLUMN_PROF)));
            subject.setSection(c.getString(c.getColumnIndex(Subject.COLUMN_SECTION)));
            subject.setTimeStart(c.getLong(c.getColumnIndex(Subject.COLUMN_TIME_START)));
            subject.setTimeEnd(c.getLong(c.getColumnIndex(Subject.COLUMN_TIME_END)));
            subject.setUntilDate(c.getLong(c.getColumnIndex(Subject.COLUMN_UNTIL_DATE)));
            subject.setClassDay(0, c.getInt(c.getColumnIndex(Subject.COLUMN_MONDAY)));
            subject.setClassDay(1, c.getInt(c.getColumnIndex(Subject.COLUMN_TUESDAY)));
            subject.setClassDay(2, c.getInt(c.getColumnIndex(Subject.COLUMN_WEDNESDAY)));
            subject.setClassDay(3, c.getInt(c.getColumnIndex(Subject.COLUMN_THURSDAY)));
            subject.setClassDay(4, c.getInt(c.getColumnIndex(Subject.COLUMN_FRIDAY)));
            subject.setClassDay(5, c.getInt(c.getColumnIndex(Subject.COLUMN_SATURDAY)));
            subject.setClassDay(6, c.getInt(c.getColumnIndex(Subject.COLUMN_SUNDAY)));
            subject.setID(id);
        }
        c.close();
        db.close();

        return subject;
    }

    public long addTask(Task task){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Task.COLUMN_SUBJ_ID, task.getSubjID());
        contentValues.put(Task.COLUMN_USER_ID, task.getUserID());
        contentValues.put(Task.COLUMN_NOTE, task.getTaskNote());
        contentValues.put(Task.COLUMN_DATE, task.getDate());

        long id = db.insert(Task.TABLE_NAME, null, contentValues);

        db.close();
        return id;
    }

    public boolean editTask(Task taskDetails, int id){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Task.COLUMN_SUBJ_ID, taskDetails.getSubjID());
        contentValues.put(Task.COLUMN_USER_ID, taskDetails.getUserID());
        contentValues.put(Task.COLUMN_NOTE, taskDetails.getTaskNote());
        contentValues.put(Task.COLUMN_DATE, taskDetails.getDate());

        int rowsAffected = db.update(Task.TABLE_NAME,
                contentValues,
                Task.COLUMN_ID + "=?",
                new String[]{taskDetails.getTaskID()+""});

        db.close();
        return rowsAffected > 0;
    }

    public boolean deleteTask(long id){
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(Task.TABLE_NAME,
                Task.COLUMN_ID + "=?", new String[]{id+""});
        db.close();
        return rowsAffected > 0;
    }

    public Task getTask(long id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Task.TABLE_NAME,
                null,
                Task.COLUMN_ID + "=?",
                new String[]{id+""},
                null,
                null,
                null);
        Task task = null;
        if(c.moveToFirst()){
            task = new Task();
            task.setUserID(c.getLong(c.getColumnIndex(Task.COLUMN_USER_ID)));
            task.setSubjID(c.getLong(c.getColumnIndex(Task.COLUMN_SUBJ_ID)));
            task.setDate(c.getLong(c.getColumnIndex(Task.COLUMN_DATE)));
            task.setTaskNote(c.getString(c.getColumnIndex(Task.COLUMN_NOTE)));
            task.setTaskID(id);
        }
        c.close();
        db.close();

        return task;
    }

    public long userRecordExist(String username) {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_USERNAME + " LIKE '" + username + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return -1;
        }

        long id = -1;
        if(cursor.moveToFirst())
             id = cursor.getLong(cursor.getColumnIndex(User.COLUMN_ID));

        cursor.close();
        return id;
    }

    public boolean checkPassword(long id, String password){
        SQLiteDatabase db = getWritableDatabase();
        String Query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + " LIKE '" + id + "' AND " + User.COLUMN_PASSWORD + " LIKE '" + password + "';";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public ArrayList<Subject> getAllUserSubjects(long id){
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        SQLiteDatabase db = getWritableDatabase();
        String Query = "SELECT * FROM " + Subject.TABLE_NAME + " WHERE " + Subject.COLUMN_USER_ID + " LIKE " + id + ";";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()){
                Subject subject = new Subject();
                subject.setID(cursor.getLong(cursor.getColumnIndex(Subject.COLUMN_ID)));
                subject.setUserID(cursor.getLong(cursor.getColumnIndex(Subject.COLUMN_USER_ID)));
                subject.setSubjectName(cursor.getString(cursor.getColumnIndex(Subject.COLUMN_NAME)));
                subject.setProfessorName(cursor.getString(cursor.getColumnIndex(Subject.COLUMN_PROF)));
                subject.setSection(cursor.getString(cursor.getColumnIndex(Subject.COLUMN_SECTION)));
                subject.setTimeStart(cursor.getLong(cursor.getColumnIndex(Subject.COLUMN_TIME_START)));
                subject.setTimeEnd(cursor.getLong(cursor.getColumnIndex(Subject.COLUMN_TIME_END)));
                subject.setUntilDate(cursor.getLong(cursor.getColumnIndex(Subject.COLUMN_UNTIL_DATE)));
                subject.setClassDay(0, cursor.getInt(cursor.getColumnIndex(Subject.COLUMN_MONDAY)));
                subject.setClassDay(1, cursor.getInt(cursor.getColumnIndex(Subject.COLUMN_TUESDAY)));
                subject.setClassDay(2, cursor.getInt(cursor.getColumnIndex(Subject.COLUMN_WEDNESDAY)));
                subject.setClassDay(3, cursor.getInt(cursor.getColumnIndex(Subject.COLUMN_THURSDAY)));
                subject.setClassDay(4, cursor.getInt(cursor.getColumnIndex(Subject.COLUMN_FRIDAY)));
                subject.setClassDay(5, cursor.getInt(cursor.getColumnIndex(Subject.COLUMN_SATURDAY)));
                subject.setClassDay(6, cursor.getInt(cursor.getColumnIndex(Subject.COLUMN_SUNDAY)));
                subjects.add(subject);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return subjects;
    }
}
