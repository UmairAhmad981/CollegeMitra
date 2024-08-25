package com.hack.collegemitra;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SubjectListener{

    ArrayList<course_card> Courses = new ArrayList<>();
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
        Course_RVAdapter adapter = new Course_RVAdapter(getContext(), Courses, this);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  // Use getContext() here

        return view;
    }

    private void setCourses() {
        Courses.clear();
        String[] AllCourses = getResources().getStringArray(R.array.Courses_Name);

        for (String course : AllCourses) {
            Courses.add(new course_card(course));
        }
    }

    @Override
    public void onSubjectClicked(int position) {
        String str = Courses.get(position).getCourse();

        String Title_toolbar=str;
        TextView tx= requireActivity().findViewById(R.id.toolbar_text);
        tx.setText(Title_toolbar);

        // Use getActivity() if FrameLay is in the activity's layout
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Make sure BatchFragment is initialized correctly
        BatchFragment batchFragment = new BatchFragment();

        // Replace the fragment in the activity's container
        fragmentTransaction.replace(R.id.FrameLay, batchFragment);
        fragmentTransaction.addToBackStack(null); // Optional: add this transaction to the back stack
        fragmentTransaction.commit();

    }
    @Override
    public void onResume() {
        super.onResume();

        // Use getActivity() to access the parent activity
        if (getActivity() != null) {
            // Find the TextView in the activity and set the title
            TextView tx = getActivity().findViewById(R.id.toolbar_text);
            if (tx != null) {
                tx.setText("Home");
            }
        }
    }


}
