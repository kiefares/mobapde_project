package ares.kiefer.parare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import ares.kiefer.parare.data.User;
import ares.kiefer.parare.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private boolean mode = false; //false = sign in || true = sign_up
    private DatabaseHelper databaseHelper;

    private EditText usernameInfo;
    private EditText passwordInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen_alternative_login);

        databaseHelper = DatabaseHelper.getInstance(this);

        final ToggleButton signInButton = (ToggleButton) findViewById(R.id.sign_in_text);
        final ToggleButton signUpButton = (ToggleButton) findViewById(R.id.sign_up_text);
        final ImageButton proceedButton = (ImageButton) findViewById(R.id.sign_in_button);
        usernameInfo = (EditText) findViewById(R.id.username_edit_text);
        passwordInfo = (EditText) findViewById(R.id.password_edit_text);

        signInButton.setChecked(true);
        signUpButton.setChecked(false);

        signUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!signUpButton.isChecked()){
                    signInButton.setChecked(false);
                    signUpButton.setChecked(true);
                    mode = true;
                }
                else{
                    signInButton.setChecked(false);
                    signUpButton.setChecked(true);
                    mode = true;
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!signInButton.isChecked()){
                    signInButton.setChecked(true);
                    signUpButton.setChecked(false);
                    mode = false;
                }
                else{
                    signInButton.setChecked(true);
                    signUpButton.setChecked(false);
                    mode = false;
                }
            }
        });

        proceedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //if sign in
                if(!mode){
                    long userID = databaseHelper.userRecordExist(usernameInfo.getText().toString());
                    if(userID == -1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("User does not exist.")
                                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog invalidAlert = builder.create();
                        invalidAlert.show();
                    }
                    else{
                        if(!databaseHelper.checkPassword(userID, passwordInfo.getText().toString())){
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Wrong password.")
                                    .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog invalidAlert = builder.create();
                            invalidAlert.show();
                        }
                        else {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
                       //     editor.putString(MainActivity.KEY_ID, usernameInfo.getText().toString());
                            editor.putLong(MainActivity.KEY_ID, userID);
                            editor.commit();

                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }
                else{ // sign up
                    if(databaseHelper.userRecordExist(usernameInfo.getText().toString()) == -1){
                        databaseHelper.addUser(new User(usernameInfo.getText().toString(), passwordInfo.getText().toString()));
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("You have created an account!")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog successAlert = builder.create();
                        successAlert.show();
                        // Enter user profile
                        usernameInfo.getText().clear();
                        passwordInfo.getText().clear();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Username is already in use.")
                                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog invalidAlert = builder.create();
                        invalidAlert.show();
                    }
                }
            }
        });
    }
}