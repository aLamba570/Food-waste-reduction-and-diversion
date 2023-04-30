package com.example.aahaar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText name, email, password;
    Button LoginBtn;
    TextView mCreate;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        LoginBtn = findViewById(R.id.button);
        mCreate = findViewById(R.id.newuser);

        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser() != null){
            Intent k = new Intent(LoginActivity.this, HomeActivity.class);
            k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(k);
            finish();
        }

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(k);
                finish();
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name, Email, Password;
                Password = password.getText().toString().trim();
                Email = email.getText().toString().trim();
                Name = name.getText().toString().trim();

                if(TextUtils.isEmpty(Password)){
                    Handler handler =new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                        }
                    },1000);
                }

                if(Password.length() < 6)
                {
                    password.setError("Password Must be >=6 Characters");
                    return;
                }

                if(TextUtils.isEmpty(Email)){
                    Handler handler =new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(LoginActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                        }
                    },1000);
                }


                if(TextUtils.isEmpty(Name)){
                    Handler handler =new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(LoginActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                        }
                    },1000);
                }


                mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent k = new Intent(LoginActivity.this, HomeActivity.class);
                            k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(k);
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Error!  ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(k);
                finish();
            }
        });
    }
}