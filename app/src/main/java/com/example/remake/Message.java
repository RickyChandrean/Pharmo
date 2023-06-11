package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Message extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        String phonetxt=intent.getStringExtra("key");
        bottomNavigationView=findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.messages);
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
                        break;
                    case R.id.setting:
                        finish();
                        Intent kesetting=new Intent(getApplicationContext(),setting.class);
                        kesetting.putExtra("key",phonetxt);
                        startActivity(kesetting);
                        overridePendingTransition(0,4);
                        break;
                }
                return false;
            }
        });
    }
}