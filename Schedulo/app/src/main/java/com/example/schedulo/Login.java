package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    private final String SERVER_URL = "http://10.0.2.2:7000/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void checkLogIn(View view) {
        EditText getPhone = findViewById(R.id.number);
        EditText getPass = findViewById(R.id.password);

        final String inputPhone = getPhone.getText().toString();
        final String inputPass = getPass.getText().toString();
        final Context ctx = this;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // action
                        MainActivity.newInstance(inputPhone, inputPass, ctx);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        Login.this.startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // action
                        System.out.println("\n\nERROR\n\n" + error.toString());
                        TextView textView = findViewById(R.id.textView3);
                        textView.setText("Invalid username or password.");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", inputPhone);
                params.put("password", inputPass);

                return params;
            }
        };
        queue.add(postRequest);
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
