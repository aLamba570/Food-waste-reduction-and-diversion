package com.example.aahaar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    CardView donate, receive, maps, aboutus, contact, logout, pins, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        donate = findViewById(R.id.cardDonate);
        receive = findViewById(R.id.cardReceive);
        maps = findViewById(R.id.cardFoodmap);
        aboutus = findViewById(R.id.cardAboutus);
        contact = findViewById(R.id.cardContact);
        logout = findViewById(R.id.cardLogout);
        pins = findViewById(R.id.cardMyPin);
        history = findViewById(R.id.cardHistory);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() ==null){
            Intent intent = new Intent(HomeActivity.this, landing.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        donate.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DonationActivity.class);
                startActivity(intent);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReceiverActivity.class);
                startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(getApplicationContext(), ContactusActivity.class);
            }
        });
    }
}