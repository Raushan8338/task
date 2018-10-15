package com.ample.ample.nps.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import com.ample.ample.nps.Actvity.AttendanceActivity;
import com.ample.ample.nps.Message;
import com.ample.ample.nps.R;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ProductViewHolder> {
    private Context mCtx;
    private List<AttendanceProduct> attendance;

    public AttendanceAdapter(Context mCtx, List<AttendanceProduct> attendance) {
        this.mCtx = mCtx;
        this.attendance = attendance;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.attendance, null);
        return new AttendanceAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final AttendanceProduct attendanceAdapter=attendance.get(position);
        holder.month.setText(attendanceAdapter.getMonth());
        holder.present.setText(attendanceAdapter.getPresent());
       holder.allday.setText(attendanceAdapter.getAllday());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mCtx, AttendanceActivity.class);
                intent.putExtra("allday",attendanceAdapter.getAllday());
                mCtx.startActivity(intent);
            }
        });

       /* holder.month.setText(attendanceAdapter.getMonth());
       *//* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mCtx, Message.class);
                intent.putExtra("message",assigmentsAdapter.getmessage());
                mCtx.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return attendance.size();

    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView month,present,allday;
        CardView cardView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            month=itemView.findViewById(R.id.month);
            present = itemView.findViewById(R.id.present);
           allday = itemView.findViewById(R.id.allday);
            cardView=itemView.findViewById(R.id.card);
        }
    }
}
