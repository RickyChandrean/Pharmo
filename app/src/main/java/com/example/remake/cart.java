package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.remake.Message;
import com.example.remake.R;
import com.example.remake.setting;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class cart extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    LinearLayout shoppingcart;

    RecyclerView rv;

    MainAdapter mainAdapter;

    ImageButton removecart;
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String phonetxt=intent.getStringExtra("key");

        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("users").child(phonetxt).child("ShoppingCart"), MainModel.class)
                        .build();

        mainAdapter=new MainAdapter(options);
        rv.setAdapter(mainAdapter);

        bottomNavigationView=findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.shoppingcart);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        finish();
                        break;
                    case R.id.shoppingcart:
                        break;
                    case R.id.messages:
                        finish();
                        Intent kepesan=new Intent(getApplicationContext(), Message.class);
                        kepesan.putExtra("key",phonetxt);
                        startActivity(kepesan);
                        overridePendingTransition(0,4);
                        break;
                    case R.id.setting:
                        finish();
                        Intent kesetting=new Intent(getApplicationContext(), setting.class);
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