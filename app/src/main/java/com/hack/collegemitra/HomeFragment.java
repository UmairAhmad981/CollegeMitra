package com.hack.collegemitra;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SubjectListener{

    ArrayList<com.hack.studentapp.course_card> Courses = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView here
        RecyclerView recyclerView = view.findViewById(R.id.Course_Cycle);

        setCourses();
        // Use getContext() to pass the context to the adapter
        com.hack.studentapp.Course_RVAdapter adapter = new com.hack.studentapp.Course_RVAdapter(getContext(), Courses, this);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  // Use getContext() here

        return view;
    }

    private void setCourses() {
        String[] AllCourses = getResources().getStringArray(R.array.Courses_Name);

        for (String course : AllCourses) {
            Courses.add(new com.hack.studentapp.course_card(course));
        }
    }

    @Override
    public void onSubjectClicked(int position) {
        String str = Courses.get(position).getCourse();

        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
