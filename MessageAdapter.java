package com.ample.ample.nps.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ample.ample.nps.R;
import com.ample.ample.nps.Message;

import java.util.List;

/**
 * Created by Raushan 07/09/2018
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product2> messageList;

    public MessageAdapter(Context mCtx, List<Product2>messageList) {
        this.mCtx = mCtx;
        this.messageList = messageList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.messagelist, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Product2 product2 = messageList.get(position);

        //loading the image

        holder.textViewTitle.setText(product2.getdate());
        holder.textViewShortDesc.setText(product2.getmessage());
       holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx,Message.class);
                intent.putExtra("message",product2.getmessage());
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc;
        CardView cardView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewdate);
            textViewShortDesc = itemView.findViewById(R.id.textViewmessage);
            cardView = itemView.findViewById(R.id.card);


        }
    }
}
