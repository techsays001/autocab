package com.msg91.sendotp.sample;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Registration extends AppCompatActivity {

    Button btn1;
    EditText e1, e2, e3, e4, e5,e6;
SharedPreferences sp;
CheckBox a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        btn1 = findViewById(R.id.buttonac);
        e1 = findViewById(R.id.nameac);
        e2 = findViewById(R.id.ageac);
        e3 = findViewById(R.id.addressac);
        e4 = findViewById(R.id.emailac);
        a=findViewById(R.id.checkBox2);
        e5 = findViewById(R.id.passwordac);
        e6 = findViewById(R.id.passwordac2);

        sp=getSharedPreferences("reg1",MODE_PRIVATE);
       Toast.makeText(Registration.this,sp.getString("phh",null),Toast.LENGTH_LONG).show();

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
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://techsaysjustin.000webhostapp.com/autocabreg.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                   // Toast.makeText(Registration.this, response, Toast.LENGTH_LONG).show();
                                    if (response.equals("Success"))

                                    {

                                        new SweetAlertDialog(Registration.this, SweetAlertDialog.WARNING_TYPE)
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
                                                                        Intent in=new Intent(Registration.this, Sining.class);
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
                            params.put("phone",sp.getString("phh",null));

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
                    RequestQueue requestQueue = Volley.newRequestQueue(Registration.this);
                    requestQueue.add(stringRequest);

                }
            }

        });

    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Registration.this,
                Manifest.permission.CALL_PHONE)) {

// Toast.makeText(Cpature_image.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Registration.this, new String[]{
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