package com.example.capstone;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPS extends AppCompatActivity {

    public String[] currentLocation(Context context, Activity activity) {

        final LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        } else {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            return new String[]{provider, longitude + "", latitude + "", altitude + ""};
        }
        return null;

    }

//    private Button button1;
//    private TextView txtResult;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gps);
//        button1 = (Button) findViewById(R.id.button1);
//        txtResult = (TextView) findViewById(R.id.txtResult);
//
//        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Build.VERSION.SDK_INT >= 23 &&
//                        ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(GPS.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                            0);
//                } else {
//                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    String provider = location.getProvider();
//                    double longitude = location.getLongitude();
//                    double latitude = location.getLatitude();
//                    double altitude = location.getAltitude();
//
//                    txtResult.setText("위치정보 : " + provider + "\n" +
//                            "위도 : " + longitude + "\n" +
//                            "경도 : " + latitude + "\n" +
//                            "고도  : " + altitude);
//
//                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                            1000,
//                            1,
//                            gpsLocationListener);
//                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                            1000,
//                            1,
//                            gpsLocationListener);
//                }
//            }
//        });
//
//    }
//
//    final LocationListener gpsLocationListener = new LocationListener() {
//        public void onLocationChanged(Location location) {
//
//            String provider = location.getProvider();
//            double longitude = location.getLongitude();
//            double latitude = location.getLatitude();
//            double altitude = location.getAltitude();
//
//            txtResult.setText("위치정보 : " + provider + "\n" +
//                    "위도 : " + longitude + "\n" +
//                    "경도 : " + latitude + "\n" +
//                    "고도  : " + altitude);
//
//        }
//
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//        }
//
//        public void onProviderEnabled(String provider) {
//        }
//
//        public void onProviderDisabled(String provider) {
//        }
//    };
}
