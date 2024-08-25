package com.hack.collegemitra;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ResourceFragment extends Fragment {

    // TextView fields
    private TextView tx1, tx2, tx3;
    private TextView b1, b2, b3;
    private ImageButton ib1, ib2, ib3;

    // Arrays of course names and topics
    String[] Cour = {"Computer Science", "Chemistry", "Physics", "Maths"};
    String[] CS = {"Data Structure", "Data Science", "Machine Learning"};
    String[] Chem = {"Inorganic", "Physical", "Organic"};
    String[] Phy = {"Mechanics", "Heat", "Electro Magnetism"};
    String[] Maths = {"Linear Algebra", "Calculus", "Differential Equation"};

    String[] CSlink = {"https://www.mta.ca/~rrosebru/oldcourse/263114/Dsa.pdf", "https://mrcet.com/downloads/digital_notes/CSE/II%20Year/DS/Introduction%20to%20Datascience%20[R20DS501].pdf", "https://alex.smola.org/drafts/thebook.pdf"};
    String[] Chemlink = {"https://tech.chemistrydocs.com/Books/InOrganic/Inorganic-Chemistry-by-James-E-House.pdf", "http://www.rnlkwc.ac.in/pdf/study-material/chemistry/Peter_Atkins__Julio_de_Paula__Physical_Chemistry__1_.pdf", "https://ncert.nic.in/textbook/pdf/kech202.pdf"};
    String[] Phylink = {"https://www.vssut.ac.in/lecture_notes/lecture1423904717.pdf", "https://www.larberthigh.com/_documents/%5B628167%5D3.3_BGE__1__-_Heat_Energy_Summary_Notes.pdf", "https://no1patna.kvs.ac.in/sites/default/files/ELECTROSTATICS%20NOTES%20%282%29.pdf.pdf"};
    String[] Mathslink = {"https://www.math.ucdavis.edu/~linear/linear-guest.pdf", "https://ocw.mit.edu/ans7870/resources/Strang/Edited/Calculus/Calculus.pdf", "https://www.math.cmu.edu/~wn0g/noll/2ch6a.pdf"};

    String[] links;
    private String batch1, Course;

    public ResourceFragment() {
        // Required empty public constructor
    }

    // Custom constructor to pass batch and course details
    public ResourceFragment(String batch1, String Course) {
        this.batch1 = batch1;
        this.Course = Course;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            batch1 = getArguments().getString("batch1");
            Course = getArguments().getString("Course");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_resource, container, false);

        // Initialize TextViews and ImageButtons
        tx1 = rootView.findViewById(R.id.ResName1);
        tx2 = rootView.findViewById(R.id.ResName2);
        tx3 = rootView.findViewById(R.id.ResName3);

        ib1 = rootView.findViewById(R.id.res1btn);
        ib2 = rootView.findViewById(R.id.res2btn);
        ib3 = rootView.findViewById(R.id.res3btn);

        b1 = rootView.findViewById(R.id.textView1);
        b2 = rootView.findViewById(R.id.textView2);
        b3 = rootView.findViewById(R.id.textView3);

        // Set batch text
        b1.setText(batch1);
        b2.setText(batch1);
        b3.setText(batch1);

        // Set the TextViews and ImageButton links based on the selected course
        for (int i = 0; i < Cour.length; i++) {
            if (Course.equals(Cour[i])) {
                switch (i) {
                    case 0:
                        links = CSlink;
                        setTextViews(CS);
                        break;
                    case 1:
                        links = Chemlink;
                        setTextViews(Chem);
                        break;
                    case 2:
                        links = Phylink;
                        setTextViews(Phy);
                        break;
                    case 3:
                        links = Mathslink;
                        setTextViews(Maths);
                        break;
                }
                break;
            }
        }

        // Set onClickListeners for the ImageButtons to open URLs
        ib1.setOnClickListener(view -> openLink(links[0]));
        ib2.setOnClickListener(view -> openLink(links[1]));
        ib3.setOnClickListener(view -> openLink(links[2]));

        return rootView;
    }

    private void setTextViews(String[] topics) {
        // Assuming there are 3 topics to be set to the TextViews
        if (topics.length >= 3) {
            tx1.setText(topics[0]);
            tx2.setText(topics[1]);
            tx3.setText(topics[2]);
        }
    }

    private void openLink(String url) {
        // Show a toast message
        Toast.makeText(getActivity(), "Downloading...", Toast.LENGTH_SHORT).show();

        // Open the link in a browser
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
