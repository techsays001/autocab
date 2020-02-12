package com.msg91.sendotp.sample;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Paysuccess extends AppCompatActivity {
    Intent i;
    String address, city, state, country, postalCode, knownName;
SharedPreferences shh,shp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccess);



        shp = getSharedPreferences("okk", MODE_PRIVATE);
        //  sh = getSharedPreferences("loc", MODE_PRIVATE);
        Toast.makeText(Paysuccess.this,shh.getString("la",null)+shh.getString("lo",null)+shh.getString("add",null), Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(Paysuccess.this,booking.class);
                startActivity(i);
                finish();
            }
        }, 3000);

        MediaPlayer m=MediaPlayer.create(Paysuccess.this,R.raw.pay);
        m.start();
        i=getIntent();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Keep track of user location.
        // Use callback/listener since requesting immediately may return null location.
        // IMPORTANT: TO GET GPS TO WORK, MAKE SURE THE LOCATION SERVICES ON YOUR PHONE ARE ON.
        // FOR ME, THIS WAS LOCATED IN SETTINGS > LOCATION.
        if (ActivityCompat.checkSelfPermission(Paysuccess.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Paysuccess.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, new Listener());
        // Have another for GPS provider just in case.
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, new Listener());
        // Try to request the location immediately
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (location != null) {
            handleLatLng(location.getLatitude(), location.getLongitude());
        }
               /* Toast.makeText(getApplicationContext(),
                        "Trying to obtain GPS coordinates. Make sure you have location services on.",
                        Toast.LENGTH_SHORT).show();*/
    }

    class Listener implements LocationListener {
        public void onLocationChanged(Location location) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            handleLatLng(latitude, longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
        /**
         * Listener for changing gps coords.
         */


    }

    /**
     * Handle lat lng.
     */
    private void handleLatLng(final double latitude, final double longitude) {
        Log.v("TAG", "(" + latitude + "," + longitude + ")");
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        city = addresses.get(0).getLocality();
        state = addresses.get(0).getAdminArea();
        country = addresses.get(0).getCountryName();
        postalCode = addresses.get(0).getPostalCode();
        knownName = addresses.get(0).getFeatureName();






















//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://techsaysjustin.000webhostapp.com/sms.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////If we are getting success from server
//                        Toast.makeText(Paysuccess.this, response, Toast.LENGTH_LONG).show();
//
//
//
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////You can handle error here if you want
//                    }
//
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
////Adding parameters to request
//
//                params.put("phone",i.getStringExtra("phone"));
//                params.put("add",address);
//                params.put("la",String.valueOf(latitude));
//                params.put("lo",String.valueOf(longitude));
//                //params.put("password",e2.getText().toString());
//
//// params.put("confpass", confpass.getText().toString());
//// params.put("phone", phone.getText().toString());
//// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();
//
////returning parameter
//                return params;
//            }
//
//        };
//
//// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
//// balance.setText(m+"");
//
//
////Adding the string request to the queue
//        RequestQueue requestQueue = Volley.newRequestQueue(Paysuccess.this);
//        requestQueue.add(stringRequest);
//
//    }
    }

}
