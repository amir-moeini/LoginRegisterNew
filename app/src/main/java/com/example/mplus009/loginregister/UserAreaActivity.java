package com.example.mplus009.loginregister;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String NAME_SHARED_PREF = "name";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView etUsername = (TextView) findViewById(R.id.etUsername);
        final TextView etAge = (TextView) findViewById(R.id.etAge);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String name = intent.getStringExtra("name").toUpperCase();
        int age = intent.getIntExtra("age", -1);

        String message = name + " Welcome to your profile";
        etUsername.setText(username);
        etAge.setText(age + "");
        welcomeMessage.setText(message);

    }

    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to name
                        editor.putString(NAME_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(UserAreaActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
