package com.example.aahaar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText name, password, email;
    Button register;
    TextView Alregisterd;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        register = findViewById(R.id.button);
        Alregisterd = findViewById(R.id.alregis);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        Alregisterd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(k);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(SignUpActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(SignUpActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                        }
                    },1000);
                }


                mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "User Created.", Toast.LENGTH_SHORT) .show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = mStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("name",Name);
                            user.put("email",Email);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess: user Profile is created for "+ userID);
                                    Toast.makeText(SignUpActivity.this, "Registered Successfully.", Toast.LENGTH_SHORT) .show();
                                }
                            });


                            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        });

        Alregisterd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}