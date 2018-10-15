package com.ample.ample.nps.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ample.ample.nps.Actvity.Fullimage;
import com.ample.ample.nps.R;
import com.bumptech.glide.Glide;


import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<ImageProduct> image;

    public ImageAdapter(Context mCtx, List<ImageProduct> image) {
        this.mCtx = mCtx;
        this.image =image;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.image, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final ImageProduct imageProduct = image.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(imageProduct.getImage())
                .into(holder.imageView);

        //holder.textViewTitle.setText(imageProduct.getdate());
        holder.textViewmessage.setText(imageProduct.getmessage());
       // holder.imageView.setImageResource();
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, Fullimage.class);
                intent.putExtra("image",imageProduct.getImage());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewmessage;
        ImageView imageView;
        CardView cardview;
        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewmessage = itemView.findViewById(R.id.textViewmessage);
            imageView = itemView.findViewById(R.id.imageView);
            cardview=itemView.findViewById(R.id.cardview);
        }
    }
}
