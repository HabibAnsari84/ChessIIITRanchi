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

public class AdminRegister extends AppCompatActivity {
    EditText useredt, emailedt, passedt;
    private String user, email, password;
    Button regbtn;
    TextView alreg;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        useredt = findViewById(R.id.edtuser);
        emailedt = findViewById(R.id.edtlog);
        passedt = findViewById(R.id.edtpass);
        regbtn = findViewById(R.id.regbtn);
        alreg = findViewById(R.id.log);

        auth = FirebaseAuth.getInstance();


        alreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminRegister.this, AdminLogin.class);
                startActivity(intent);
            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();


            }
        });
    }

    private void validateUser() {
        user = useredt.getText().toString();
        email = emailedt.getText().toString();
        password = passedt.getText().toString();
        if(user.isEmpty()||email.isEmpty()||password.isEmpty()){
            Toast.makeText(AdminRegister.this,"Enter all the fields",Toast.LENGTH_LONG).show();
        }
        else{
            registerUser();
        }
    }
    private void registerUser()
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AdminRegister.this, "Registration Successful",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(AdminRegister.this,AdminLogin.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AdminRegister.this,"Error in Registration"+task.getException().getMessage()
                            ,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}