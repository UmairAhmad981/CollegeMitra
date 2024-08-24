package com.hack.collegemitra;

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
 * Use the {@link BatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BatchFragment extends Fragment implements BatchListener {
    ArrayList<batch_card> Batches = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

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
        RecyclerView recyclerView = view.findViewById(R.id.Batch_Cycle);
        setBatches();

        Batch_RVAdapter adapter = new Batch_RVAdapter(getContext(), Batches, this); // `this` is now valid
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void setBatches() {
        String[] AllBatches = getResources().getStringArray(R.array.Batch_Wise);

        for (String batch : AllBatches) {
            Batches.add(new batch_card("Batch" + batch));
        }
    }

    @Override
    public void onBatchClicked(int position) {
        // Handle batch click event here
        // For example, show a toast or update the UI
        String selectedBatch = Batches.get(position).getBatch();
        Toast.makeText(getContext(), "Clicked: " + selectedBatch, Toast.LENGTH_SHORT).show();
    }
}
