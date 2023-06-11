package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class setting extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CardView accountsetting,avoutus,help,history;
    TextView nametop;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String phonetxt=intent.getStringExtra("key");

        bottomNavigationView=findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.setting);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        finish();
                        break;
                    case R.id.shoppingcart:
                        finish();
                        Intent kecart=new Intent(getApplicationContext(), cart.class);
                        kecart.putExtra("key",phonetxt);
                        startActivity(kecart);
                        overridePendingTransition(4,4);
                        break;
                    case R.id.messages:
                        finish();
                        Intent kepesan=new Intent(getApplicationContext(),Message.class);
                        kepesan.putExtra("key",phonetxt);
                        startActivity(kepesan);
                        overridePendingTransition(0,4);
                        break;
                    case R.id.setting:
                        break;
                }
                return false;
            }
        });
        accountsetting=findViewById(R.id.accountsetting);
        accountsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),Profile.class);
                intent1.putExtra("key",phonetxt);
                startActivity(intent1);

            }
        });

        avoutus=findViewById(R.id.avoutus);
        avoutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),aboutus.class);
                intent1.putExtra("key",phonetxt);
                startActivity(intent1);

            }
        });

        help=findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),helpCenter.class);
                intent1.putExtra("key",phonetxt);
                startActivity(intent1);

            }
        });

        history=findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),History.class);
                intent1.putExtra("key",phonetxt);
                startActivity(intent1);

            }
        });

        nametop=findViewById(R.id.nametop);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String getName=snapshot.child(phonetxt).child("Name").getValue(String.class);
                nametop.setText(getName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}