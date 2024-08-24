package com.hack.studentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hack.collegemitra.R;

import java.util.ArrayList;

public class Course_RVAdapter extends RecyclerView.Adapter<Course_RVAdapter.MyViewHolder> {
    private Context context; // Ensure this is set correctly
    private ArrayList<com.hack.studentapp.course_card> Cour;

    public Course_RVAdapter(Context context, ArrayList<com.hack.studentapp.course_card> Cour) { // Accept Context
        this.context = context;
        this.Cour = Cour;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Courses_c.setText(Cour.get(position).getCourse());
    }

    @Override
    public int getItemCount() {
        return Cour.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Courses_c;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Courses_c = itemView.findViewById(R.id.cor);
        }
    }
}
