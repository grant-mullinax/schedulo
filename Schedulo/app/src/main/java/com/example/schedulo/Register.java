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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private final String SERVER_URL = "http://10.0.2.2:7000/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerUser(View view) {
        // Collect log in info
        EditText getName = findViewById(R.id.name);
        EditText getPhone = findViewById(R.id.number);
        EditText getPass = findViewById(R.id.password);

        final String inputName = getName.getText().toString();
        final String inputPhone = getPhone.getText().toString();
        final String inputPass = getPass.getText().toString();
        final TextView textView = findViewById(R.id.textView);

        if (inputPhone.length() < 8 || inputPhone.length() > 25) {
            textView.setText("Phone number must have 8 to 25 digits.");
           // Intent intent = new Intent(Register.this, Register.class);
           // Register.this.startActivity(intent);
        }
        else {
            final Context ctx = this;

            // Serialize
            User newUser = new User(inputName, inputPhone, inputPass);
            Gson gson = new Gson();
            final String userString = gson.toJson(newUser);

            // Post to server
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest postRequest = new StringRequest(Request.Method.POST, SERVER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("\n\nSUCCESS\n\n" + response);
                            MainActivity.newInstance(inputPhone, inputPass, ctx);
                            Intent intent = new Intent(Register.this, Login.class);
                            Register.this.startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("\n\nERROR\n\n" + error.toString());
                            textView.setVisibility(View.VISIBLE);
                            textView.setText("Could not complete request at this time.");
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
    }
}
