package com.example.vc;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
EditText let,let1;
Button lb;
TextView ltv;
FirebaseAuth mfire;
FirebaseAuth.AuthStateListener mauthlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        let=findViewById(R.id.username);
        let1=findViewById(R.id.password);
        lb=findViewById(R.id.login);
        ltv=findViewById(R.id.textview2);
       mfire=FirebaseAuth.getInstance();
       mauthlist=new FirebaseAuth.AuthStateListener() {

           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser muser=mfire.getCurrentUser();
               if(muser!=null)
               {
                   Toast.makeText(login.this, "you are logged in.", Toast.LENGTH_SHORT).show();
                   Intent in=new Intent(login.this,MainActivity.class);
                   startActivity(in);
               }
               else
               {
                   Toast.makeText(login.this, "Please sign up!", Toast.LENGTH_SHORT).show();
                   ltv.requestFocus();
               }
           }
       };
        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=let.getText().toString();
                String pass=let1.getText().toString();
                if(email.isEmpty())
                {
                    let.setError("Please enter email id");
                    let.requestFocus();
                }
                else if(pass.isEmpty())
                {
                    let1.setError("Please enter password");
                    let1.requestFocus();
                }
                else if(email.isEmpty() && pass.isEmpty())
                {
                    Toast.makeText(login.this, "Please provide email and password", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pass.isEmpty()))
                {
                    mfire.signInWithEmailAndPassword(email,pass).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                       if(!(task.isSuccessful()))
                       {
                           Toast.makeText(login.this, "Error occured", Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           Intent in =new Intent(login.this,MainActivity.class);
                           startActivity(in);
                       }
                        }
                    });

                }
                else
                {
                    Toast.makeText(login.this, "Unexpected error occured! please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ltv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(login.this,signup.class);
                startActivity(in);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        mfire.addAuthStateListener(mauthlist);
    }
}
