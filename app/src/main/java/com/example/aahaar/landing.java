package com.example.aahaar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class landing extends AppCompatActivity {

    CardView cardLogin,cardRegister,cardAbout;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        cardAbout = findViewById(R.id.cardAboutus);
        cardLogin = findViewById(R.id.cardLogin);
        cardRegister = findViewById(R.id.cardRegister);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            Intent k = new Intent(landing.this, HomeActivity.class);
            k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(k);
            finish();
        }


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