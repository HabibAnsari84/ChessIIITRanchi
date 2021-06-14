package com.example.chessiiitranchi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    EditText emaillogin,passwordlogin ;
    Button logbtn1;
    TextView txt;
    FirebaseAuth auth1;
    private String email , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        logbtn1 = findViewById(R.id.logbtn);
        emaillogin = findViewById(R.id.edtlog);
        passwordlogin = findViewById(R.id.edtpass);
        txt = findViewById(R.id.reg);

        auth1 = FirebaseAuth.getInstance();

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this, AdminRegister.class);
                startActivity(intent);
            }
        });

        logbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth1.getCurrentUser()!= null){
            Intent intent = new Intent(AdminLogin.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private void validateUser() {
        email = emaillogin.getText().toString();
        password = passwordlogin.getText().toString();
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(AdminLogin.this,"Enter all the fields",Toast.LENGTH_LONG).show();
        }
        else{
            LoginUser();
        }
    }
    private void LoginUser(){
        auth1.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AdminLogin.this,"Login Successful ",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdminLogin.this,MainActivity.class );
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AdminLogin.this,"Error in Registration"+task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}