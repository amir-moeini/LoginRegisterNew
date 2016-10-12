package com.example.mplus009.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN ACTIVITY";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    //This would be used to store the name of current logged in user
    public static final String NAME_SHARED_PREF = "name";
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        Log.d(TAG, "User Login information has been fetch from the screen");

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                Log.d(TAG, "User Clicked on Register Button");
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respones) {
                        try {
                            JSONObject jsonResponse = new JSONObject(respones);
                            Boolean success = jsonResponse.getBoolean("success");
                            if(success) {

                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");



                                //If we are getting success from server
                                //Creating a shared preference
                                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editor
                                editor.putBoolean(LOGGEDIN_SHARED_PREF, true);
                                editor.putString(NAME_SHARED_PREF, name);

                                //Saving values to editor
                                editor.commit();




                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("name",name);
                                intent.putExtra("age", age);
                                intent.putExtra("username",username);

                                LoginActivity.this.startActivity(intent);
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry",null)
                                        .show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };


                LoginRequest loginRequest = new LoginRequest(username,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
                Log.d(TAG, "Login Request has benn Added to the Queue");

            }
        });
    }

    protected void onResume() {
            super.onResume();
            //In on resume fetching value from sharedpreference
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

            //Fetching the boolean value form sharedpreferences
            loggedIn = sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF, false);

            //If we will get true
            if(loggedIn){
                //We will start the Profile Activity
                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        }

}
