package com.example.vc;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    LinearLayout l1;
    Button b,logout;
    int i;
    EditText et;
    DatabaseReference myRef;
    FirebaseDatabase database;
    FirebaseUser fuser;
    FirebaseAuth mfire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.b);
        et=findViewById(R.id.et);
        l1=findViewById(R.id.l1);
        logout=findViewById(R.id.logout);
        //Intent in=new Intent(getApplicationContext(),login.class);
      //  Bundle extras=getIntent().getExtras();
       // final String user=extras.getString("user");
       // final String phno=extras.getString("phno");
       // Toast.makeText(getApplicationContext(), "welcome "+user, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "phno"+phno, Toast.LENGTH_SHORT).show();
        database = FirebaseDatabase.getInstance();
        //String uid=mfire.getInstance().getUid();

        //fuser=FirebaseAuth.getInstance().getCurrentUser();
         //myRef = database.getReference("message");
        myRef = database.getReference("message");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                TextView tvf=new TextView(MainActivity.this);
                tvf.setText(value);
                l1.addView(tvf);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }



        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "you pressed the send button", Toast.LENGTH_SHORT).show();
               String s=et.getText().toString();
               //TextView tv=new TextView(MainActivity.this);
                //tv.setText(s);
               // l1.addView(tv);
                et.setText("");
                myRef = database.getReference("message");
                myRef.setValue(s);
                //i++;

                //////////////////////////////////////////////////////////
                //////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////
                //TextView tvf=new TextView(MainActivity.this);
                //tvf.setText();



            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfire.getInstance().signOut();
                Intent in =new Intent(MainActivity.this,login.class);
                startActivity(in);
            }
        });

    }

}
