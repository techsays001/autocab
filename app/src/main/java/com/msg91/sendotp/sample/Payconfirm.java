package com.msg91.sendotp.sample;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Payconfirm extends AppCompatActivity implements PaymentResultListener {
    TextView n,m;
    Button b;
    Intent i;
    SharedPreferences shh;
    EditText text, text1, text2, dob;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payconfirm);
        n=findViewById(R.id.textView7);
        m=findViewById(R.id.textView8);
        b=findViewById(R.id.button);
        text1 = findViewById(R.id.fplaceac1);
        text2 = findViewById(R.id.timeac1);
        dob = findViewById(R.id.Dateac1);

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(Payconfirm.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                text2.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        });


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(Payconfirm.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dob.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        i=getIntent();
        n.setText(i.getStringExtra("name"));;
        m.setText(i.getStringExtra("i"));

        shh = getSharedPreferences("ok", MODE_PRIVATE);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Checkout co = new Checkout();

                try {
                    JSONObject options = new JSONObject();
                    options.put("name", "Razorpay Corp");
                    options.put("description", "Demoing Charges");
                    //You can omit the image option to fetch the image from dashboard
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                    options.put("currency", "INR");
                    options.put("amount", Integer.parseInt(i.getStringExtra("i"))*100);

                    JSONObject preFill = new JSONObject();
                    preFill.put("email", "test@razorpay.com");
                    preFill.put("contact", "9876543210");

                    options.put("prefill", preFill);

                    co.open(Payconfirm.this, options);
                } catch (Exception e) {
                    Toast.makeText(Payconfirm.this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                    e.printStackTrace();
                }
            }
        });



    }

    @Override
    public void onPaymentSuccess(String s) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://techsaysjustin.000webhostapp.com/autocab_user_location.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
                        //
                        Toast.makeText(Payconfirm.this,response, Toast.LENGTH_LONG).show();
                        if (response.equals("Success"))

                        {
text1.getText().clear();
text2.getText().clear();
dob.getText().clear();
                            //Toast.makeText(Payconfirm.this, response+"data added", Toast.LENGTH_LONG).show();
                        }
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");


                            }
//
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

                params.put("la",shh.getString("la",null));
                params.put("lo",shh.getString("lo",null));
                params.put("add",shh.getString("add",null));
                params.put("eadd", text1.getText().toString());
                params.put("date",text2.getText().toString());
                params.put("time",dob.getText().toString());
                params.put("kl",i.getStringExtra("name"));
                // params.put("",shh.getString("time",null));

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
        RequestQueue requestQueue = Volley.newRequestQueue(Payconfirm.this);
        requestQueue.add(stringRequest);

        //startActivity(new Intent(Payconfirm.this,Paysuccess.class).putExtra("phone",i.getStringExtra("phone")));
    }



    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(Payconfirm.this,"Payment Error",Toast.LENGTH_LONG).show();
    }
}
