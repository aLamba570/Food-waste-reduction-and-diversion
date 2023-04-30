package com.example.aahaar;

import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DonationActivity extends AppCompatActivity {

    private GoogleMap Map;
    GoogleApiClient mGoogleApiClient;
    Location LastLocation;
    LocationRequest LocationRequest;
    private int REQUEST_CODE = 11;
    SupportMapFragment mapFragment;
    EditText FullName,FoodItem,Description,Phone;
    Button SubmitBtn;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    String userID;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        FullName = findViewById(R.id.name);
        FoodItem = findViewById(R.id.foodname);
        Description = findViewById(R.id.description);
        Phone = findViewById(R.id.phonenumber);
        SubmitBtn = findViewById(R.id.submit);


        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION));
    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        Map = googleMap;
//        buildGoogleApiClient();
//    }

//    private void buildGoogleApiClient() {
//    }
}