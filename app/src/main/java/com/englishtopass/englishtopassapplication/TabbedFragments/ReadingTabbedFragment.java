package com.englishtopass.englishtopassapplication.TabbedFragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.TabbedAdapters.TabbedListeningRecyclerAdapter;
import com.englishtopass.englishtopassapplication.Adapters.TabbedAdapters.TabbedReadingRecyclerAdapter;
import com.englishtopass.englishtopassapplication.Model.Reading.Package.ReadingPackage;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.ViewModels.ReadingViewModel;

import java.util.List;


public class ReadingTabbedFragment extends Fragment {
    private static final String TAG = "ReadingTabbedFragment";

    private ReadingViewModel readingViewModel;
    private RecyclerView recyclerView;

    public ReadingTabbedFragment() {
        // Required empty public constructor
    }

    public static ReadingTabbedFragment newInstance() {
        return new ReadingTabbedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tabbed_reading_fragment, container, false);

        recyclerView = view.findViewById(R.id.readingRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        recyclerView.setHasFixedSize(true);

        final TabbedReadingRecyclerAdapter adapter = new TabbedReadingRecyclerAdapter(getContext(), (AppCompatActivity) getActivity());

        recyclerView.setAdapter(adapter);

        readingViewModel = ViewModelProviders.of(this).get(ReadingViewModel.class);

        readingViewModel.getReadingPackageLiveData().observe(this, new Observer<List<ReadingPackage>>() {
            @Override
            public void onChanged(List<ReadingPackage> readingPackages) {

                adapter.submitList(readingPackages);

            }
        });

        return view;
    }

}


