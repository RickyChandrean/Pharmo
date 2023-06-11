package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {

    EditText editname,editemail,editpass,editphone,confirmpass;
    TextView loginredirect;
    Button  signupbottom;
    DatabaseReference reference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://pharmacy-cddbf-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        signupbottom=findViewById(R.id.signupbottom);
        editname=findViewById(R.id.editname);
        editemail=findViewById(R.id.editemail);
        editpass=findViewById(R.id.editpass);
        editphone=findViewById(R.id.editphone);
        confirmpass=findViewById(R.id.confirmpass);
        loginredirect=findViewById(R.id.loginredirect);
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        signupbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("users");

                final String name = editname.getText().toString();
                final String email = editemail.getText().toString();
                final String pass = editpass.getText().toString();
                final String phone = editphone.getText().toString();
                final String conpass = confirmpass.getText().toString();

                if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(signup.this, "Please fill all the field", Toast.LENGTH_SHORT).show();
                } else if (email.trim().matches(EMAIL_PATTERN)){
                    boolean statecheckpw=checkPassword(pass,conpass);
                    if(statecheckpw==true){
                        if (!pass.equals(conpass)) {
                            Toast.makeText(signup.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                        } else {
                            if (phone.length()>10&phone.length()<13){
                                {
                                    reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.hasChild(phone)) {
                                                Toast.makeText(signup.this, "Phone number is already registered", Toast.LENGTH_SHORT).show();

                                            } else {
                                                reference.child(phone).child("Name").setValue(name);
                                                reference.child(phone).child("Email").setValue(email);
                                                reference.child(phone).child("Password").setValue(pass);
                                                reference.child(phone).child("phone").setValue(phone);

                                                Toast.makeText(signup.this, "Sign up successfull", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),login.class));
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(signup.this, "Phone number should more that 10 digit", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else {
                    Toast.makeText(signup.this, "Input a valid email", Toast.LENGTH_SHORT).show();
                }
            }
        });


        loginredirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(signup.this,login.class);
                overridePendingTransition(5,5);
                startActivity(intent);
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