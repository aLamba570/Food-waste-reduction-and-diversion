package com.example.aahaar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class landing extends AppCompatActivity {

    CardView cardLogin,cardRegister,cardAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        cardAbout = findViewById(R.id.cardAboutus);
        cardLogin = findViewById(R.id.cardLogin);
        cardRegister = findViewById(R.id.cardRegister);


        cardRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(landing.this, SignUpActivity.class);
                startActivity(k);
                finish();
            }
        });

        cardLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(landing.this, LoginActivity.class);
                startActivity(k);
                finish();
            }
        });


        cardAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(landing.this, AboutActivity.class);
                startActivity(k);
                finish();
            }
        });


    }
}