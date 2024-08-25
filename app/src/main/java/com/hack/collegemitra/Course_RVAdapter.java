package com.hack.collegemitra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hack.collegemitra.R;
import com.hack.collegemitra.SubjectListener;

import java.util.ArrayList;

public class Course_RVAdapter extends RecyclerView.Adapter<Course_RVAdapter.MyViewHolder> {
    private final SubjectListener subjectListener;
    private final Context context;
    private final ArrayList<course_card> Cour;

    public Course_RVAdapter(Context context, ArrayList<course_card> Cour, SubjectListener subjectListener) {
        this.context = context;
        this.Cour = Cour;
        this.subjectListener = subjectListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);  // Use 'context' here
        View view = inflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(view, subjectListener);
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
        final TextView Courses_c;

        public MyViewHolder(@NonNull View itemView, SubjectListener subjectListener) {
            super(itemView);
            Courses_c = itemView.findViewById(R.id.cor);
            itemView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (subjectListener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            subjectListener.onSubjectClicked(pos);
                        }
                    }
                }
            });
        }
    }
}
