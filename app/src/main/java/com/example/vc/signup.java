package com.example.vc;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class signup extends AppCompatActivity {
EditText semail,spass;
Button sb;
TextView stv;
FirebaseAuth mfire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        semail=findViewById(R.id.usernames);
        spass=findViewById(R.id.passwords);
        sb=findViewById(R.id.logins);
        stv=findViewById(R.id.textview);
       mfire=FirebaseAuth.getInstance();
    sb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email=semail.getText().toString();
            String pass=spass.getText().toString();
            if(email.isEmpty())
            {
                semail.setError("Please enter email id");
                semail.requestFocus();
            }
            else if(pass.isEmpty())
            {
                spass.setError("Please enter password");
                spass.requestFocus();
            }
            else if(email.isEmpty() && pass.isEmpty())
            {
                Toast.makeText(signup.this, "Please provide email and password", Toast.LENGTH_SHORT).show();
            }
            else if(!(email.isEmpty() && pass.isEmpty()))
            {
                mfire.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(!(task.isSuccessful()))
                   {
                       Toast.makeText(signup.this, "Sign up unsuccessfull! Please try again later ", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Intent in=new Intent(signup.this,login.class);
                       startActivity(in);
                   }

                    }
                });

            }
            else
            {
                Toast.makeText(signup.this, "Unexpected error occured! please try again later", Toast.LENGTH_SHORT).show();
            }
        }

    });
    stv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent in=new Intent(signup.this,login.class);
            startActivity(in);
        }
    });
    }

}
