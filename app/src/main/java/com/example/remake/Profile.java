package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    ImageButton arrowback;
    CardView accountsetting,passwordsetting,emailganti;
    TextView namedisplay,passwordDisplay,emaildisplay,phonedisplay;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
    ImageView viewpw,hidepw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        arrowback=findViewById(R.id.arrowback);
        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String phonetxt=intent.getStringExtra("key");

        namedisplay=findViewById(R.id.namedisplay);
        passwordDisplay=findViewById(R.id.passwordDisplay);
        emaildisplay=findViewById(R.id.emaildisplay);
        phonedisplay=findViewById(R.id.phonedisplay);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String getName=snapshot.child(phonetxt).child("Name").getValue(String.class);
                final String getPass=snapshot.child(phonetxt).child("Password").getValue(String.class);
                final String getEmail=snapshot.child(phonetxt).child("Email").getValue(String.class);
                final String getPhone=snapshot.child(phonetxt).child("phone").getValue(String.class);
                namedisplay.setText(getName);
                passwordDisplay.setText(getPass);
                emaildisplay.setText(getEmail);
                phonedisplay.setText(getPhone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        viewpw=findViewById(R.id.viewpw);
        hidepw=findViewById(R.id.hidepw);
        viewpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordDisplay.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                viewpw.setVisibility(View.GONE);
                hidepw.setVisibility(View.VISIBLE);
            }
        });

        hidepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordDisplay.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                viewpw.setVisibility(View.VISIBLE);
                hidepw.setVisibility(View.GONE);
            }
        });

        accountsetting=findViewById(R.id.accountsetting);
        accountsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send=new Intent(getApplicationContext(),changeName.class);
                send.putExtra("key",phonetxt);
                startActivity(send);
            }
        });
        passwordsetting=findViewById(R.id.passwordsetting);
        passwordsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send=new Intent(getApplicationContext(),changePass.class);
                send.putExtra("key",phonetxt);
                startActivity(send);
            }
        });
        emailganti=findViewById(R.id.emailganti);
        emailganti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send=new Intent(getApplicationContext(),changeEmail.class);
                send.putExtra("key",phonetxt);
                startActivity(send);
            }
        });

    }
}