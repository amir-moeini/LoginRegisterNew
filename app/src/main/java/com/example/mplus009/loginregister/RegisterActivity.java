package com.example.mplus009.loginregister;

import android.app.DownloadManager;
import android.content.Intent;
import android.service.voice.VoiceInteractionSession;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public class RegisterActivity extends AppCompatActivity {

//    private static final String TAG = "REGISTER ACTIVITY";

    public static final String REGISTER_URL = "http://10.0.2.2:8080/Register.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_AGE = "age";
    public static final String KEY_NAME = "name";

    private EditText etAge;
    private EditText etName;
    private EditText etUsername;
    private EditText etPassword;

    private Button bRegister;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    private void registerUser() {

        final String name = etName.getText().toString();
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();
        final int age = Integer.parseInt(etAge.getText().toString());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d(TAG, "after receiving response from Volley");
                            JSONObject jsonResponse = new JSONObject(response);
                            //When the request is executed and volley gave us Response,
                            // it will get the value of "success" and make that equal to Boolean variable success.
                            Boolean success = jsonResponse.getBoolean("success");
                            Log.d(TAG, "jsonResponse value is: " + "");
                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry",null)
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {


            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_NAME, name);
                params.put(KEY_USERNAME, username);
                params.put(KEY_PASSWORD, password);
                params.put(KEY_AGE, age + "");
                return params;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    @Override
    public void onClick(View v){
            if (v == bRegister) {
                registerUser();
            }
        }
    }*/
























    private static final String TAG = "REGISTER ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        Log.d(TAG, "User Registration information has been fetch from the screen");

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());

                Response.Listener<String> responselistener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d(TAG, "after receiving response from Volley");
                            JSONObject jsonResponse = new JSONObject(response);
                            //When the request is executed and volley gave us Response,
                            // it will get the value of "success" and make that equal to Boolean variable success.
                            Boolean success = jsonResponse.getBoolean("success");
                            Log.d(TAG, "jsonResponse value is: " + "");
                            if(success){
                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry",null)
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Log.d(TAG, "All the parameters are ready to pass to RegisterRequest");

                RegisterRequest registerrequest = new RegisterRequest(name, username, age, password, responselistener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                Log.d(TAG, "Adding Request to the Queue");
                queue.add(registerrequest);
                Log.d(TAG, "Request has benn Added to the Queue");


            }
        });

    }}

