package com.example.mplus009.loginregister;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mplus009 on 06/07/2016.
 */
public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://10.0.2.2:8080/Login.php";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);            //passing the value that Volley need to Register.php

        //Preparing the parameter that we have to pass to Register.php
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    //Volly needs to access the data we have just set, so:


    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
