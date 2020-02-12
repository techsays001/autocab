package com.msg91.sendotp.sample;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter2 extends RecyclerView.Adapter<Chequeadapter2.ProductViewHolder> {
    Intent i;


    private Context mCtx;
    private List<Cheque2> productList;

    public Chequeadapter2(Context mCtx, List<Cheque2> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c1, null);
        return new ProductViewHolder(view);








    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final   Cheque2 cheque;   cheque = productList.get(position);


        holder.username.setText(cheque.getPrize1());
        holder.userphone.setText(cheque.getPrize2());
        holder.useradddress.setText(cheque.getPrize3());

        holder.eadd.setText(cheque.getPrize4());

        sh= mCtx.getSharedPreferences("data",MODE_PRIVATE);

        holder.usermap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+cheque.getPrize1()+","+cheque.getPrize2()));
                mCtx.startActivity(intent);







            }
        });



    }




    @Override
    public int getItemCount() {
        return productList.size();
    }
    SharedPreferences sh;
    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView username,userphone,useradddress,usermap,userla,userlo,eadd;





        public ProductViewHolder(View itemView) {
            super(itemView);

           username = itemView.findViewById(R.id.username);
           eadd= itemView.findViewById(R.id.username2);
            userphone= itemView.findViewById(R.id.userph);
           useradddress= itemView.findViewById(R.id.useraddress);
            usermap= itemView.findViewById(R.id.usermap);
            userla= itemView.findViewById(R.id.userlangitude1);
            userlo= itemView.findViewById(R.id.userloongitude);





        }


    }

}