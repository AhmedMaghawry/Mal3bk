package com.ezzat.mla3bk;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Registeration extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText age;
    private EditText mobile;
    private EditText nationalId;
    private TextView locationTV;
    private Button location;
    private Button confirm;
    private Button cancel;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        intializeViews();
        buttonsActions();
    }

    private void buttonsActions() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Registeration.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentLocation = getCurrentLocation();
                locationTV.setText(currentLocation);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkValidForm(name.getText().toString(), email.getText().toString(), Integer.parseInt(age.getText().toString()), mobile.getText().toString(), nationalId.getText().toString(), locationTV.getText().toString())) {
                        User user = new User(name.getText().toString(), email.getText().toString(), Integer.parseInt(age.getText().toString()), mobile.getText().toString(), nationalId.getText().toString(), locationTV.getText().toString());
                        uploadUserToDatabase(user);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid information", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error in enter", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private String getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

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
        });
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return location.getLatitude()+","+location.getLongitude();
    }

    private boolean checkValidForm(String name, String email, int age, String mobile, String nationalId, String location) {
        boolean res = true;
        if(name == null || name.length() < 4) {
            this.name.setError("Invalid input");
            res = false;
        }
        if(email == null || !email.contains("@") || !email.contains(".com") || !checkDatabaseIfExsistEmail(email)) {
            this.email.setError("Invalid input");
            res = false;
        }
        if(age < 10 || age > 90) {
            this.age.setError("Invalid input");
            res = false;
        }
        if(mobile == null || mobile.length() != 11) {
            this.mobile.setError("Invalid input");
            res = false;
        }if(nationalId == null || nationalId.length() != 14 || !checkDatabaseIfExsistNID(nationalId)) {
            this.nationalId.setError("Invalid input");
            res = false;
        }if(location == null) {
            this.location.setError("Invalid input");
            res = false;
        }
        String pass = password.getText().toString();
        String rePass = confirmPassword.getText().toString();
        if(password.length() < 8 || !pass.equals(rePass)) {
            this.password.setError("Invalid input");
            this.confirmPassword.setError("Invalid input");
            res = false;
        }
        return res;
    }

    private void intializeViews() {
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.repassword);
        age = (EditText) findViewById(R.id.age);
        mobile = (EditText) findViewById(R.id.mobile);
        nationalId = (EditText) findViewById(R.id.nid);
        location = (Button) findViewById(R.id.location);
        confirm = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);
        locationTV = (TextView) findViewById(R.id.loc);
    }
}
