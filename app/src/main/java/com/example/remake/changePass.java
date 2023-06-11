package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class changePass extends AppCompatActivity {
    EditText editpass,newpass,confirmpass;
    Button confirm_button;
    ImageButton arrowback;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);


        editpass=findViewById(R.id.editpass);
        newpass=findViewById(R.id.newpass);
        confirmpass=findViewById(R.id.confirmpass);
        confirm_button=findViewById(R.id.confirm_button);
        arrowback=findViewById(R.id.arrowback);

        Intent intent = getIntent();
        String phonetxt=intent.getStringExtra("key");
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final String getpass=snapshot.child(phonetxt).child("Password").getValue(String.class);
                        boolean statecheckpw=checkPassword(newpass.getText().toString(),confirmpass.getText().toString());
                        if(statecheckpw==true){
                            if (!getpass.equals(confirmpass.getText().toString())){

                                AlertDialog.Builder builder=new AlertDialog.Builder(changePass.this);
                                builder.setCancelable(false);
                                builder.setTitle("Notice");
                                builder.setMessage("Change Password into "+ confirmpass.getText().toString());
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        databaseReference.child(phonetxt).child("Password").setValue(confirmpass.getText().toString());
                                        Toast.makeText(changePass.this, "Password change successfull", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                // Show the Alert Dialog box
                                alertDialog.show();

                            } else {
                                Toast.makeText(changePass.this, "Input different Password", Toast.LENGTH_SHORT).show();
                            }
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
    public boolean checkPassword(String password, String confirmPassword) {
        boolean state = false;

        int passLength = password.length();
        if (passLength >= 6) {
            if (confirmPassword.length() != 0) {
                if (password.equals(confirmPassword)) {
                    state = true;
                } else {
                    Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    confirmpass.setText("");
                    editpass.setText("");
                    editpass.requestFocus();
                }
            } else {
                Toast.makeText(this, "Please enter confirm password",
                        Toast.LENGTH_SHORT).show();
                confirmpass.setText("");
                confirmpass.requestFocus();
                state = false;
            }
        } else {
            Toast.makeText(this, "Please enter 6 digit password",
                    Toast.LENGTH_SHORT).show();
            editpass.setText("");
            editpass.requestFocus();
            state = false;
        }
        return state;
    }
}
