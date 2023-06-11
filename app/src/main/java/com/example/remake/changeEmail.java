package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class changeEmail extends AppCompatActivity {
    EditText editname;
    Button confirm_button;
    ImageButton arrowback;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        getSupportActionBar().hide();


        editname=findViewById(R.id.editname);
        confirm_button=findViewById(R.id.confirm_button);
        String email = editname.getText().toString();
        Intent intent = getIntent();
        String phonetxt=intent.getStringExtra("key");
        //take the data of the current name
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final String getEmail=snapshot.child(phonetxt).child("Email").getValue(String.class);
                        if (!getEmail.equals(editname.getText().toString())){
                            if(Patterns.EMAIL_ADDRESS.matcher(editname.getText().toString()).matches()){
                                AlertDialog.Builder builder=new AlertDialog.Builder(changeEmail.this);
                                builder.setCancelable(false);
                                builder.setTitle("Notice");
                                builder.setMessage("Change Email into "+ editname.getText().toString());
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        databaseReference.child(phonetxt).child("Email").setValue(editname.getText().toString());
                                        Toast.makeText(changeEmail.this, "Email change successfull", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                // Show the Alert Dialog box
                                alertDialog.show();
                            }
                        } else {
                            Toast.makeText(changeEmail.this, "Input different email", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        arrowback=findViewById(R.id.arrowback);
        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}