package com.ample.ample.nps.Fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class PayAdapter extends RecyclerView.Adapter {
    public PayAdapter(Context context, List<PayProduct> pay) {
    }

    public PayAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }



    @Override
    public int getItemCount() {
        return 0;
    }
}
