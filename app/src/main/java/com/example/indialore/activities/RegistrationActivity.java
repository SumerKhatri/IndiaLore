package com.example.indialore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.indialore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    EditText name,email,password;
    private FirebaseAuth auth;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            Toast.makeText(RegistrationActivity.this,"Logged In Successfully!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            finish();
        }
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        sharedPreferences=getSharedPreferences("OnboardingScreen",MODE_PRIVATE);
        boolean isFirstTime=sharedPreferences.getBoolean("firstTime",true);
        if(isFirstTime){
            sharedPreferences.edit().putBoolean("firstTime",false).commit();
            startActivity(new Intent(RegistrationActivity.this,OnboardingActivity.class));
            finish();
        }
    }
    public void signup(View view){
        String userName=name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();
        if(userName.isEmpty()){
            Toast.makeText(this,"Please enter user name",Toast.LENGTH_SHORT).show();
            return;
        }if(userEmail.isEmpty()){
            Toast.makeText(this,"Please enter your email id",Toast.LENGTH_SHORT).show();
            return;
        }if(userPassword.isEmpty()){
            Toast.makeText(this,"Please enter a password",Toast.LENGTH_SHORT).show();
            return;
        }if(userPassword.length()<8){
            Toast.makeText(this,"Minimum password length should be 8",Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this,"Successfully Registered!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(RegistrationActivity.this,"Registration Failed:"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });
//        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));

    }
    public void signin(View view){
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }
}