package com.msg91.sendotp.sample;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Registrationauto extends AppCompatActivity {

    Button btn1;
    EditText e1, e2, e3, e4, e5,e6;
SharedPreferences shh;
CheckBox a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationauto);
        btn1 = findViewById(R.id.buttonac1);
        e1 = findViewById(R.id.nameac1);
        e2 = findViewById(R.id.ageac1);
        e3 = findViewById(R.id.addressac1);
        e4 = findViewById(R.id.emailac1);
        a=findViewById(R.id.checkBox21);
        e5 = findViewById(R.id.passwordac1);
        e6 = findViewById(R.id.passwordac21);

        shh=getSharedPreferences("regat",MODE_PRIVATE);
       Toast.makeText(Registrationauto.this,shh.getString("pho",null),Toast.LENGTH_LONG).show();

        EnableRuntimePermission();
        a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    e5.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    a.setText("Hide");
                }
                else
                {

                    e5.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    a.setText("Show");
                }

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().isEmpty()) {
                    e1.setError("null");
                }
              else  if (e2.getText().toString().isEmpty()) {
                    e2.setError("null");

                }
             else   if (e3.getText().toString().isEmpty()) {
                    e3.setError("null");
                }
             else   if (e4.getText().toString().isEmpty()) {
                    e4.setError("null");
                }
            else    if (e5.getText().toString().isEmpty()) {
                    e5.setError("null");
                }

                else if(!(e5.getText().toString().equals(e6.getText().toString()))){

                    Toast.makeText(getApplicationContext(), "Password Noy Match ", Toast.LENGTH_LONG).show();

                }


            else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://techsaysjustin.000webhostapp.com/autocab_auto_registration.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    Toast.makeText(Registrationauto.this, response, Toast.LENGTH_LONG).show();
                                    if (response.equals("Success"))

                                    {

                                        new SweetAlertDialog(Registrationauto.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Registration Success")
                                                .setContentText("back to layout!")
                                                .setConfirmText("Yes,Login")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        sDialog
                                                                .setTitleText("Logining...!")

                                                                .setConfirmText("OK")

                                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                    @Override
                                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                        Intent in=new Intent(Registrationauto.this, Siningauto.class);
                                                                        startActivity(in);
                                                                    }
                                                                })
                                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                    }
                                                })
                                                .show();




//

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

                            params.put("autouser", e1.getText().toString());
                            params.put("autoage", e2.getText().toString());
                            params.put("autoaddress", e3.getText().toString());
                            params.put("autoemail", e4.getText().toString());
                            params.put("autopass", e5.getText().toString());
                            params.put("phone",shh.getString("pho",null));

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
                    RequestQueue requestQueue = Volley.newRequestQueue(Registrationauto.this);
                    requestQueue.add(stringRequest);

                }
            }

        });

    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Registrationauto.this,
                Manifest.permission.CALL_PHONE)) {

// Toast.makeText(Cpature_image.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Registrationauto.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);


        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case 1:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

// Toast.makeText(Cpature_image.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}