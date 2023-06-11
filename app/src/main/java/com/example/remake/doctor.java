package com.example.remake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class doctor extends AppCompatActivity {

    int [] doctorImage={
        R.drawable.doktergigi,
            R.drawable.dokterjantung,
            R.drawable.dokterkulit,
            R.drawable.dokterkandungan,
            R.drawable.dokterhematologi,
            R.drawable.dokterbedah,
            R.drawable.doktersaraf,
            R.drawable.digestif,
            R.drawable.psikolog
    };

    String[]namescategory={
            "Dentist",
            "Cardiovascular",
            "Ophthalmologists",
            "Perinatologist",
            "Hematologist",
            "Surgery",
            "Neurologist",
            "Gastroenterologist",
            "Psychiatrist"
    };

    String [] waktukerja={
            "09:00-12.00",
            "10:00-14:00",
            "10:00-14:00",
            "12:00-14:00",
            "16:00-19:00",
            "16:00-20:00",
            "12:00-16:00",
            "09:00-12:00",
            "09:00-12:00"
    };

    String [] docName={
            "drg. Yuliana Helena",
            "dr. A. M. Onny Witjaksono, SpJP, FIHA",
            "dr. F.X. Sutardi Santosa, SpKK. FINSDV",
            "dr. Ekarini Aryasatiani, SpOG (K)",
            "dr. Hardjo Prawiro, SpPD, KKV",
            "dr. Josef Soenarta, SpB",
            "dr. Parlin Susanto, SpS",
            "dr. Ronald A. Hukom, SpPD, KHOM",
            "Clarissa Tania S., Psi, M. Psi"
    };

    LinearLayout doctorlist;
    ImageView circularimage;
    TextView namedoctor,dokterspesialis,jamkerja,biayakonsul,toptext;

    ImageButton arrowback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        getSupportActionBar().hide();
        Intent intent=getIntent();
        String key=intent.getStringExtra("key");
        int yangdipilih= Arrays.asList(namescategory).indexOf(key);

        toptext=findViewById(R.id.toptext);
        toptext.setText(namescategory[yangdipilih]);

        doctorlist=findViewById(R.id.doctorlist);
        loadDoctor(yangdipilih);

        arrowback=findViewById(R.id.arrowback);
        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void loadDoctor(int key) {
        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.activity_doctor_view,doctorlist,false);
        namedoctor=view.findViewById(R.id.namedoctor);
        dokterspesialis=view.findViewById(R.id.dokterspesialis);
        jamkerja=view.findViewById(R.id.jamkerja);
        circularimage=view.findViewById(R.id.circularimage);
        circularimage.setImageResource(doctorImage[key]);
        namedoctor.setText(docName[key]);
        jamkerja.setText(waktukerja[key]);
        dokterspesialis.setText(namescategory[key]);
        doctorlist.addView(view);
    }
//
//    public void loadDoctor(){
//
//    }
}