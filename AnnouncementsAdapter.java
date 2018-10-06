package com.ample.ample.nps.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ample.ample.nps.R;
import com.ample.ample.nps.Message;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.ProductViewHolder> {
    private Context mCtx;
    private List<AnnouncementsProduct> announcements;

    public AnnouncementsAdapter(Context mCtx, List<AnnouncementsProduct> announcements) {
        this.mCtx = mCtx;
        this.announcements =announcements;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.announcements, null);
        return new AnnouncementsAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
      final AnnouncementsProduct announcementsProduct=announcements.get(position);

        holder.textViewdate.setText(announcementsProduct.getdate());
        holder.textViewmessage.setText(announcementsProduct.getmessage());
        holder.cadview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mCtx, Message.class);
                intent.putExtra("message",announcementsProduct.getmessage());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return announcements.size();

    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewdate, textViewmessage;
        CardView cadview;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewdate = itemView.findViewById(R.id.textViewdate);
            textViewmessage = itemView.findViewById(R.id.textViewmessage);
            cadview = itemView.findViewById(R.id.cadview);
        }
    }
    public void updatelist(List<String> newList)
    {
        announcements=new ArrayList<>();
        announcements.add((AnnouncementsProduct) newList);
        notifyDataSetChanged();
    }
}
