package com.hack.collegemitra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hack.collegemitra.BatchListener;
import com.hack.collegemitra.R;
import com.hack.collegemitra.batch_card;

import java.util.ArrayList;

public class Batch_RVAdapter extends RecyclerView.Adapter<Batch_RVAdapter.MyViewHolder> {
    private final BatchListener batchListener;
    private Context context;
    private ArrayList<batch_card> batc;

    public Batch_RVAdapter(Context context, ArrayList<batch_card> batc, BatchListener batchListener) {
        this.context = context;
        this.batc = batc;
        this.batchListener = batchListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // Make sure this XML file has a CardView as its root element
        View view = inflater.inflate(R.layout.batch_card, parent, false);
        return new MyViewHolder(view, batchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Batches_c.setText(batc.get(position).getBatch());
    }

    @Override
    public int getItemCount() {
        return batc.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Batches_c;

        public MyViewHolder(@NonNull View itemView, BatchListener batchListener) {
            super(itemView);
            // Ensure this ID matches the ID of the TextView in the XML layout
            Batches_c = itemView.findViewById(R.id.bat);
            itemView.findViewById(R.id.batch_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (batchListener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            batchListener.onBatchClicked(pos);
                        }
                    }
                }
            });
        }
    }
}
