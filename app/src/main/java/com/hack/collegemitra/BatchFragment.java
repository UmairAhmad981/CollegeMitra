package com.hack.collegemitra;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BatchFragment extends Fragment implements BatchListener {
    ArrayList<batch_card> Batches = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // Store the subject name for reverting
    private String subjectName;

    public BatchFragment() {
        // Required empty public constructor
    }

    public static BatchFragment newInstance(String param1, String param2) {
        BatchFragment fragment = new BatchFragment();
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
        View view = inflater.inflate(R.layout.fragment_batch, container, false);

        // Initialize the subject name
        TextView tx = requireActivity().findViewById(R.id.toolbar_text);
        if (subjectName == null) {
            subjectName = tx.getText().toString();  // Store the original subject name if not already set
        }

        // Reset the toolbar title to the subject name
        tx.setText(subjectName);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.Batch_Cycle);

        // Initialize the list only if it's empty
        if (Batches.isEmpty()) {
            setBatches();
        }

        Batch_RVAdapter adapter = new Batch_RVAdapter(getContext(), Batches, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setBatches() {
        String[] AllBatches = getResources().getStringArray(R.array.Batch_Wise);

        for (String batch : AllBatches) {
            Batches.add(new batch_card("Batch " + batch));
        }
    }

    @Override
    public void onBatchClicked(int position) {
        String selectedBatch = Batches.get(position).getBatch();

        // Update the toolbar title to the selected batch
        TextView tx = requireActivity().findViewById(R.id.toolbar_text);
        String course = subjectName;  // Use stored subject name as the course

        // Create a new instance of ResourceFragment with the selected batch and course
        ResourceFragment resourceFragment = new ResourceFragment(selectedBatch, course);

        // Update the toolbar title to the selected batch
        tx.setText(selectedBatch);

        // Replace the current fragment with ResourceFragment
        getParentFragmentManager().beginTransaction()
                .replace(R.id.FrameLay, resourceFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();  // Ensure the transaction is committed even if state loss occurs
    }

    @Override
    public void onResume() {
        super.onResume();

        // Reset the toolbar title to the subject name when returning to this fragment
        TextView tx = requireActivity().findViewById(R.id.toolbar_text);
        tx.setText(subjectName);

        // Debug: Print to log to confirm this method is being called
        System.out.println("BatchFragment onResume() called. Toolbar text reset to subject name: " + subjectName);
    }
}
