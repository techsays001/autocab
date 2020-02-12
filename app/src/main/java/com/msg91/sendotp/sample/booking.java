package com.msg91.sendotp.sample;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class booking extends AppCompatActivity {
    EditText text;
    Button btn;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    SharedPreferences sh,shh;

    String address, city, state, country, postalCode, knownName;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        text = findViewById(R.id.placeac);
        btn = findViewById(R.id.goac);
















//        sh = getSharedPreferences("loc", MODE_PRIVATE);
//        SharedPreferences.Editor ed = sh.edit();
        shh = getSharedPreferences("ok", MODE_PRIVATE);
        SharedPreferences.Editor edd = shh.edit();

        LocationManager locationManager = (LocationManager) booking.this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(booking.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(booking.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // return root;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, new booking.Listener());
        // Have another for GPS provider just in case.
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, new booking.Listener());
        // Try to request the location immediately
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (location != null) {
            handleLatLng(location.getLatitude(), location.getLongitude());
            edd.putString("la", String.valueOf(location.getLatitude()));
            edd.putString("lo", String.valueOf(location.getLongitude()));
            edd.putString("add",address);
            edd.apply();

        }
               /* Toast.makeText(getApplicationContext(),
                        "Trying to obtain GPS coordinates. Make sure you have location services on.",
                        Toast.LENGTH_SHORT).show();*/

    }

    /**
     * Handle lat lng.
     */
    private void handleLatLng(final double latitude, final double longitude) {
        Log.v("TAG", "(" + latitude + "," + longitude + ")");
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(booking.this, Locale.getDefault());

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

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(address);
            }
        });





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), vehicles.class);
                startActivity(i);


            }

            ;

        });
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
    }



}
