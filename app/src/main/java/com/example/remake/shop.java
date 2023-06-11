package com.example.remake;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.remake.databinding.ActivityShopBinding;

import org.w3c.dom.Text;

public class shop extends AppCompatActivity{

    CardView cardshop;
    ImageButton btn,shoppingcart;

    private ActivityShopBinding binding;

    int num;

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

    ImageView picshop;
    TextView tv_name_product, tv_quantity_product,tv_price_product;

    TextView toptext;
    String phonetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        cardshop = findViewById(R.id.cardshop);
        cardshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopDetail();
            }
        });
        btn = (ImageButton) findViewById(R.id.arrowback);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toptext=findViewById(R.id.toptext);
        picshop=findViewById(R.id.picshop);
        tv_name_product=findViewById(R.id.tv_name_product);
        tv_quantity_product=findViewById(R.id.tv_quantity_product);
        tv_price_product=findViewById(R.id.tv_price_product);

        Intent intent = getIntent();
        num = intent.getIntExtra("key",0);
        phonetxt=intent.getStringExtra("phone");
        toptext.setText(drugsNamesItemCategory[num]);

        picshop.setImageResource(drugImageCategory[num]);
        tv_name_product.setText(drugsNamesItemCategory[num]);
        tv_quantity_product.setText(Drugsvarianitemcategory[num]);
        tv_price_product.setText(drugsPricestvItemCategory[num]);

        shoppingcart=findViewById(R.id.shoppingcart);
        shoppingcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kecart=new Intent(getApplicationContext(),cart.class);
                kecart.putExtra("key",phonetxt);
                startActivity(kecart);
                finish();
            }
        });
    }

    public void shopDetail(){
        Intent shopDetail=new Intent(getApplicationContext(), shop_detail.class);
        shopDetail.putExtra("phone",phonetxt);
        shopDetail.putExtra("key",num);
        startActivity(shopDetail);
    }

}
