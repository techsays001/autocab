package com.msg91.sendotp.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Chequeadapter extends RecyclerView.Adapter<Chequeadapter.ProductViewHolder> {
Intent i;
SharedPreferences shh;
        private Context mCtx;
        private List<Cheque> productList;

        public Chequeadapter(Context mCtx, List<Cheque> productList) {

            this.mCtx = mCtx;
            this.productList = productList;
// sh=mCtx.getSharedPreferences("Official1",MODE_PRIVATE);
//            shh = mCtx.getSharedPreferences("ok", MODE_PRIVATE);
//            Toast.makeText(mCtx,shh.getString("la",null), Toast.LENGTH_LONG).show();
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.activity_vechicle, null);
            return new ProductViewHolder(view);


        }

        @Override
        public void onBindViewHolder(final ProductViewHolder holder, int position) {
            final Cheque cheque = productList.get(position);


            Picasso.get().load(cheque.getStatus()).into(holder.imageView);
            holder.autoid.setText(cheque.getImage());
            holder.mincharge.setText(cheque.getUser());

            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCtx.startActivity(new Intent(mCtx,Payconfirm.class).putExtra("name",cheque.getImage()).putExtra("i",cheque.getUser()).putExtra("phone",cheque.getPhone()));
                }
            });
        }


        @Override
        public int getItemCount() {
            return productList.size();
        }



        class ProductViewHolder extends RecyclerView.ViewHolder {



            ImageView imageView;
            TextView autoid,mincharge;
            Button btn;


            public ProductViewHolder(View itemView) {
                super(itemView);

                imageView=itemView.findViewById(R.id.imageView);
                autoid=itemView.findViewById(R.id.autoid);
                mincharge= itemView.findViewById(R.id.mincharge);
                btn=itemView.findViewById(R.id.confirmbtn);


            }


        }

        public void filterList(ArrayList<Cheque> filteredList) {
            productList = filteredList;
            notifyDataSetChanged();
        }

    }


