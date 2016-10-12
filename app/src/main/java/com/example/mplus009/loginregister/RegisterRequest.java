package com.example.mplus009.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mplus009 on 17/05/2016.
 */
public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://10.0.2.2:8080/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);            //passing the value that Volley need to Register.php

        //Preparing the parameter that we have to pass to Register.php
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age + "");
    }

    //Volly needs to access the data we have just set, so:


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
