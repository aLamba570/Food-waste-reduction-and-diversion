package com.example.aahaar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ContactusActivity extends AppCompatActivity {

    EditText name, message, email;
    TextInputLayout nameError, messageError,emailError;
    Button Submitbtn;
    boolean isNameValid, isMessageValid, isEmailValid;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    String userID;
    public static final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        name = (EditText) findViewById(R.id.name);
        message = (EditText) findViewById(R.id.message);
        email = (EditText) findViewById(R.id.mEmail);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        messageError = (TextInputLayout) findViewById(R.id.MessageError);

        Submitbtn = (Button) findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();


        Submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetValidation();
            }
        });


    }

    public void SetValidation() {
        if(name.getText().toString().isEmpty()){
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        }
        else {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }


        if(email.getText().toString().isEmpty()){
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())  {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        }
        else{
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }


        if (message.getText().toString().isEmpty()) {
            messageError.setError(getResources().getString(R.string.phone_error));
            isMessageValid = false;
        } else  {
            isMessageValid = true;
            messageError.setErrorEnabled(false);
        }


        if (isNameValid && isEmailValid && isMessageValid ){
            String Name, Email, Message;
             Name = name.getText().toString().trim();
             Email= email.getText().toString().trim();
             Message= message.getText().toString().trim();
             userID = mAuth.getCurrentUser().getUid();

            CollectionReference collectionReference = mStore.collection("contact data");
            Map<String,Object> user = new HashMap<>();
            user.put("timestamp", FieldValue.serverTimestamp());
            user.put("name",Name);
            user.put("email",Email);
            user.put("message",Message);
            user.put("userid",userID);

            collectionReference.add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"Successfully! We will shortly revert you back.");
                            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Intent intent = new Intent(ContactusActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error!", e);
                        }
                    });
        }

    }
}