package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {

    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://pharmacy-cddbf-default-rtdb.firebaseio.com/");
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText loginphone=findViewById(R.id.loginphone);
        final EditText loginpass=findViewById(R.id.loginpass);
        final Button loginbutton=findViewById(R.id.loginbutton);
        final TextView signupredirect=findViewById(R.id.signupredirect);
        phone= String.valueOf(loginphone);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phonetxt=loginphone.getText().toString();
                final String passTxt=loginpass.getText().toString();

                if(phonetxt.isEmpty()||passTxt.isEmpty()){
                    Toast.makeText(login.this, "Please fill all the field", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phonetxt)){
                                final String getPass=snapshot.child(phonetxt).child("Password").getValue(String.class);
//                            assert getPass != null;
                                if(getPass.equals(passTxt)){
                                    Toast.makeText(login.this, "Login in success", Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(login.this,MainActivity.class);
                                    intent.putExtra("key",phonetxt);
                                    startActivity(intent);
                                    overridePendingTransition(0,4);
                                    finish();
                                } else {
                                    Toast.makeText(login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(login.this, "Wrong phone number", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        signupredirect.setOnClickListener(v -> {
            finish();
            Intent intent=new Intent(login.this,signup.class);
            overridePendingTransition(0,0);
            startActivity(intent);
        });

    }

}