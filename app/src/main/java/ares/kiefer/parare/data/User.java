package ares.kiefer.parare.data;

/**
 * Created by Kiefer on 11/12/2017.
 */

public class User {

    public static final String TABLE_NAME = "app_users";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    private long id;
    private String username;
    private String password;

    public User(){
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setID(long id){
        this.id = id;
    }

    public long getID(){
        return id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
