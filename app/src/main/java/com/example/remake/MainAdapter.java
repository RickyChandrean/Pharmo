package com.example.remake;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    int[] drugImageCategory={
            R.drawable.panadol,
            R.drawable.bodrex,
            R.drawable.betadine,
            R.drawable.siladex,
            R.drawable.viks,
            R.drawable.insto
    };

    String[] drugsNamesItemCategory={" Panadol "," Bodrex "," Betadine "," Siladex "," Vicks "," Insto "};

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {

        holder.img.setImageResource(drugImageCategory[Arrays.asList(drugsNamesItemCategory).indexOf(model.getProductName())]);
        holder.productName.setText(model.getProductName());
        holder.productType.setText(model.getProductType());
        holder.productPrice.setText(model.getProductPrice());
        holder.productQuantity.setText(model.getProductQuantity());




    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcart,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView productName,productType,productPrice,productQuantity;
        ImageButton removecart;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.img);
            productName=(TextView) itemView.findViewById(R.id.productName);
            productType=(TextView) itemView.findViewById(R.id.productType);
            productPrice=(TextView) itemView.findViewById(R.id.productPrice);
            productQuantity=(TextView) itemView.findViewById(R.id.productQuantity);
        }
    }
}
