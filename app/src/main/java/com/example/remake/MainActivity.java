package com.example.remake;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;

    LinearLayout linear_layout_categories,linear_layout_popular;
    ImageView categories_images;
    TextView categories_text;

    int[]imagescategories={
            R.drawable.premolar,
            R.drawable.cardio,
            R.drawable.eyes,
            R.drawable.fetus,
            R.drawable.hematology,
            R.drawable.medical_tools,
            R.drawable.neuron,
            R.drawable.colon,
            R.drawable.mental_disability
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

    ImageView item_shop_images;
    TextView item_shop_text_product,item_shop_text_quantity,item_shop_text_price;

    int[] drugImageCategory={
            R.drawable.panadol,
            R.drawable.bodrex,
            R.drawable.betadine,
            R.drawable.siladex,
            R.drawable.viks,
            R.drawable.insto
    };

    String[] drugsNamesItemCategory={" Panadol "," Bodrex "," Betadine "," Siladex "," Vicks "," Insto "};
    String[] Drugsvarianitemcategory={" 1 Strip "," 1 Strip "," 30ml "," 60ml "," 10gr "," 7.5ml"};
    String[] drugsPricestvItemCategory={" Rp 12.000 "," Rp 5.000 "," Rp 25.000 "," Rp 13.000 "," Rp 10.000"," Rp 12.500"};

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
    TextView nametop;

    CircleImageView circleImageView;
    BottomNavigationView bottomNavigationView;
    String phonetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        phonetxt=intent.getStringExtra("key");

        linear_layout_categories=findViewById(R.id.linear_layout_categories);
        loadItemCategory();

        linear_layout_popular=findViewById(R.id.linear_layout_popular);
        Loaddrugcategory();

        bottomNavigationView=findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        break;
                    case R.id.shoppingcart:
                        Intent kecart=new Intent(getApplicationContext(), cart.class);
                        kecart.putExtra("key",phonetxt);
                        startActivity(kecart);
                        overridePendingTransition(4,4);
                        break;
                    case R.id.messages:
                        Intent kepesan=new Intent(getApplicationContext(),Message.class);
                        kepesan.putExtra("key",phonetxt);
                        startActivity(kepesan);
                        overridePendingTransition(0,4);
                        break;
                    case R.id.setting:
                        Intent kesetting=new Intent(getApplicationContext(),setting.class);
                        kesetting.putExtra("key",phonetxt);
                        startActivity(kesetting);
                        overridePendingTransition(0,4);
                        break;

                }
                return false;
            }
        });
        //set the top name into the name in the database
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

        //move from mainactivity to profile activity and send extra intent
        circleImageView=findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Profile.class);
                intent.putExtra("key",phonetxt);
                startActivity(intent);
            }
        });
    }


    public void loadItemCategory(){
        LayoutInflater inflater=LayoutInflater.from(this);
        int i;
        for(i=0;i<imagescategories.length;i++){
            View view=inflater.inflate(R.layout.categories_item,linear_layout_categories,false);
            categories_images=view.findViewById(R.id.categories_images);
            categories_text=view.findViewById(R.id.categories_text);
            categories_images.setImageResource(imagescategories[i]);
            categories_text.setText(namescategory[i]);
            final int aux = i;
            categories_images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selected=namescategory[aux];
                    Toast.makeText(MainActivity.this, namescategory[aux], Toast.LENGTH_SHORT).show();
                    Intent doc=new Intent(getApplicationContext(),doctor.class);
                    doc.putExtra("key",selected);
                    startActivity(doc);

                }
            });
            linear_layout_categories.addView(view);

        }
    }

    public void Loaddrugcategory(){
        LayoutInflater inflater=LayoutInflater.from(this);
        for(int i=0;i<drugImageCategory.length;i++){
            View view=inflater.inflate(R.layout.item_shop,linear_layout_popular,false);
            item_shop_images=view.findViewById(R.id.item_shop_images);
            item_shop_text_product=view.findViewById(R.id.item_shop_text_product);
            item_shop_text_quantity=view.findViewById(R.id.item_shop_text_quantity);
            item_shop_text_price=view.findViewById(R.id.item_shop_text_price);

            item_shop_images.setImageResource(drugImageCategory[i]);
            item_shop_text_product.setText(drugsNamesItemCategory[i]);
            item_shop_text_quantity.setText(Drugsvarianitemcategory[i]);
            item_shop_text_price.setText(drugsPricestvItemCategory[i]);
            final int aux=i;

            item_shop_images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String selected=drugsNamesItemCategory[aux];
                    Toast.makeText(getApplicationContext(),"Item"+selected,Toast.LENGTH_SHORT).show();
                    Intent shop=new Intent(MainActivity.this, com.example.remake.shop.class);
                    int number=aux;
                    shop.putExtra("key", number);
                    shop.putExtra("phone",phonetxt);
                    startActivity(shop);
                }
            });
            linear_layout_popular.addView(view);
        }
    }


}