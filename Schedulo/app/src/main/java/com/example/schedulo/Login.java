package com.example.schedulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    private static final String SERVER_URL = "http://localhost:7000/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void checkLogIn(View view) {
        EditText getPhone = findViewById(R.id.number);
        EditText getPass = findViewById(R.id.password);

        String inputPhone = getPhone.getText().toString();
        String inputPass = getPass.getText().toString();

        // TODO: Verify log in
        // TODO: If false, display log in failure message
        // TODO: If true, proceed to app activity
    }

    public void registerUser(View view) {
        // Collect log in info
        EditText getName = findViewById(R.id.name);
        EditText getPhone = findViewById(R.id.number);
        EditText getPass = findViewById(R.id.password);

        final String inputName = getName.getText().toString();
        final String inputPhone = getPhone.getText().toString();
        final String inputPass = getPass.getText().toString();

        // Serialize
        User newUser = new User(inputName, inputPhone, inputPass);
        Gson gson = new Gson();
        final String userString = gson.toJson(newUser);

        // Post to server
        final TextView textView = findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText("Welcome, " + inputName + "!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Could not complete request at this time.");
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() {
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
