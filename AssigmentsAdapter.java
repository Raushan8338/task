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

import java.util.List;

import com.ample.ample.nps.Actvity.Announcement;
import com.ample.ample.nps.Actvity.Assignment;
import com.ample.ample.nps.Actvity.AttendanceActivity;
import com.ample.ample.nps.R;
import com.ample.ample.nps.Message;


public class AssigmentsAdapter extends RecyclerView.Adapter<AssigmentsAdapter.ProductViewHolder> {
    private Context mCtx;
    private List<AssignmentsProduct> assignements;

    public AssigmentsAdapter(Context mCtx, List<AssignmentsProduct> assignements) {
        this.mCtx = mCtx;
        this.assignements = assignements;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.assignments, null);
        return new AssigmentsAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final AssignmentsProduct assigmentsAdapter=assignements.get(position);
        holder.textViewlanguage.setText(assigmentsAdapter.getlanguage());
        holder.textViewdate.setText(assigmentsAdapter.getdate());
        holder.textViewmessage.setText(assigmentsAdapter.getmessage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mCtx, Assignment.class);
                intent.putExtra("message",assigmentsAdapter.getmessage());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignements.size();

    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewdate,textViewlanguage, textViewmessage;
        CardView cardView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewlanguage=itemView.findViewById(R.id.textViewlanguage);
            textViewdate = itemView.findViewById(R.id.textViewdate);
            cardView=itemView.findViewById(R.id.cardview);
            textViewmessage = itemView.findViewById(R.id.textViewmessage);
        }
    }
}
