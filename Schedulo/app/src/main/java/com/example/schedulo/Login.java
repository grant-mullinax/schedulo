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

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    private static final String SERVER_URL = "http://10.0.2.2:7000";

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

        final String loginURL = SERVER_URL + "/login";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest getRequest = new StringRequest(Request.Method.POST, loginURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // action
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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", inputPhone);
                params.put("password", inputPass);

                return params;
            }
        };
        queue.add(getRequest);
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
