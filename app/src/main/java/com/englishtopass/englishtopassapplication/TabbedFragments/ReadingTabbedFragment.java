package com.englishtopass.englishtopassapplication.TabbedFragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.R;


public class ReadingTabbedFragment extends Fragment {
    private static final String TAG = "ReadingTabbedFragment";

    public ReadingTabbedFragment() {
        // Required empty public constructor
    }

    public static ReadingTabbedFragment newInstance() {
        ReadingTabbedFragment fragment = new ReadingTabbedFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            Log.d(TAG, "onCreate: filler");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tabbed_reading_fragment, container, false);
    }

}
