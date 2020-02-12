package com.msg91.sendotp.sample;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sining extends AppCompatActivity {

    Button btn;
    TextView textView,auto;
    EditText e1,e2;
    private boolean loggedIn = false;
    SharedPreferences sharedPreferences,sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sining);
        EnableRuntimePermission();
        e1=findViewById(R.id.signupcab);
        e2=findViewById(R.id.passwordcab);
        textView=findViewById(R.id.notauser);
        auto=findViewById(R.id.auto123);
        sh=getSharedPreferences("Official1",MODE_PRIVATE);
        loggedIn=sh.getBoolean("ph",false);
        sharedPreferences=getSharedPreferences("phone",MODE_PRIVATE);
        if (loggedIn) {
            startActivity(new Intent(Sining.this,selection.class));
            // Snackbar.make(v,"Enter emergency number",Snackbar.LENGTH_SHORT).show();

        }


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sining.this, MainActivity.class);
                startActivity(i);
            }
        });

      auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sining.this, Siningauto.class);
                startActivity(i);
            }
        });



        btn=findViewById(R.id.Loginautoocab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (e1.getText().toString().isEmpty()) {
                            e1.setError("please insert valid credentials");
                        }
                        if (e2.getText().toString().isEmpty()) {
                            e2.setError("please insert valid credentials");
                        }
                        else
                        {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://techsaysjustin.000webhostapp.com/loginautocab.php",
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
//If we are getting success from server

                                            if(response.contains("Success"))
                                            {
                                                startActivity(new Intent(Sining.this,selection.class));
                                            }
                                            else {
                                                Toast.makeText(Sining.this, "Incorrect Details", Toast.LENGTH_LONG).show();
                                            }
                                            try {
                                                JSONArray jsonArray = new JSONArray(response);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");


                                                }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                            } catch (JSONException e) {
                                                e.printStackTrace();

                                            }


                                        }
                                    },

                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                        }

                                    }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
//Adding parameters to request

                                    params.put("username",e1.getText().toString());
                                    params.put("password",e2.getText().toString());

// params.put("confpass", confpass.getText().toString());
// params.put("phone", phone.getText().toString());
// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                                    return params;
                                }

                            };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                            RequestQueue requestQueue = Volley.newRequestQueue(Sining.this);
                            requestQueue.add(stringRequest);

                        }




                    }
                });

            }
        });

    }
    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(Sining.this),
                Manifest.permission.CAMERA)) {

// Toast.makeText(Cpature_image.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Sining.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 12);


        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case 12:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

// Toast.makeText(Cpature_image.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Sining.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}


