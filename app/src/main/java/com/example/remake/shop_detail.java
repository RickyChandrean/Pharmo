package com.example.remake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.StringValue;

public class shop_detail extends AppCompatActivity {

    ImageButton backbutton,add,delete;
    TextView toptext,tv_name_product_shop_detail;
    TextView tv_pill_product_shop_detail,productdetail,product_price;
    TextView total;
    ImageView image;
    int[] drugImageCategory={
            R.drawable.panadol,
            R.drawable.bodrex,
            R.drawable.betadine,
            R.drawable.siladex,
            R.drawable.viks,
            R.drawable.insto
    };

    String[] drugsNamesItemCategory={" Panadol "," Bodrex "," Betadine "," Siladex "," Vicks "," Insto "};
    String[] Drugsvarianitemcategory={" 1 Strip "," 1 Strip "," 30ml "," 60ml "," 50gr "," 7.5ml"};
    String[] drugsPricestvItemCategory={" Rp 12.000 "," Rp 5.000 "," Rp 25.000 "," Rp 13.000 "," Rp 43.000"," Rp 12.500"};
    String[] detailproduct={
            "Panadol is a drug that is useful for relieving headaches, toothaches, muscle aches, nagging pains, and reducing fever. Several variants of Panadol can also relieve flu symptoms, such as nasal congestion and sneezing accompanied by a cough without phlegm.",
            "Bodrex is a drug containing Paracetamol and Caffeine. This medication is used to relieve headaches, toothaches, and reduce fever.",
            "Betadine solution contains the active substance Povidone Iodine 10%. Betadine solution is an antiseptic for wounds to kill germs that cause infection.",
            "Siladex is a syrup drug used to relieve coughs without phlegm or dry coughs accompanied by allergies. This drug contains Dextromethorphan HBr and Diphenhydramine HCL. Dextromethorphan is an antitussive that works to suppress the cough reflex. Diphenhydramine HCL is an antihistamine which has the effect of relieving itching in the throat.",
            "Vicks vaporub is a liniment to relieve cold and cough symptoms due to the flu.",
            "Insto dry eyes contains the active substances Hydroxyprophylmethyl cellulose and Benzalkonium Chloride, used to treat dry eye symptoms by providing a lubricating effect like tears."
    };

    int jumlah=0;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
    String phonetxt;

    Button addtoBasket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        getSupportActionBar().hide();

        backbutton=findViewById(R.id.arrowback);
        toptext=findViewById(R.id.toptext);
        tv_name_product_shop_detail=findViewById(R.id.tv_name_product_shop_detail);
        tv_pill_product_shop_detail=findViewById(R.id.tv_pill_product_shop_detail);
        productdetail=findViewById(R.id.productdetail);
        product_price=findViewById(R.id.product_price);
        image=findViewById(R.id.image);
        add=findViewById(R.id.add);
        delete=findViewById(R.id.delete);
        total=findViewById(R.id.total);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah++;
                total.setText(String.valueOf(jumlah));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jumlah>0){
                    jumlah--;
                    total.setText(String.valueOf(jumlah));
                }
            }
        });

        Intent intent=getIntent();
        int num=intent.getIntExtra("key",0);
        image.setImageResource(drugImageCategory[num]);
        toptext.setText(drugsNamesItemCategory[num]);
        tv_name_product_shop_detail.setText(drugsNamesItemCategory[num]);
        tv_pill_product_shop_detail.setText(Drugsvarianitemcategory[num]);
        productdetail.setText(detailproduct[num]);
        product_price.setText(drugsPricestvItemCategory[num]);


        phonetxt=intent.getStringExtra("phone");
        String productName=tv_name_product_shop_detail.getText().toString();
        String productTypes=tv_pill_product_shop_detail.getText().toString();

        String productPrice=product_price.getText().toString();
        addtoBasket=findViewById(R.id.addtoBasket);
        DatabaseReference reference = databaseReference.child(phonetxt).child("ShoppingCart");
        addtoBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String produtQuantity= String.valueOf(jumlah);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(productName)){
                            reference.child(productName).child("ProductQuantity").setValue(produtQuantity);
                            Toast.makeText(shop_detail.this, "Updated", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            reference.child(productName).child("ProductName").setValue(productName);
                            reference.child(productName).child("ProductType").setValue(productTypes);
                            reference.child(productName).child("ProductQuantity").setValue(produtQuantity);
                            reference.child(productName).child("ProductPrice").setValue(productPrice);
                            Toast.makeText(shop_detail.this, "Added", Toast.LENGTH_SHORT).show();
                            finish();
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}