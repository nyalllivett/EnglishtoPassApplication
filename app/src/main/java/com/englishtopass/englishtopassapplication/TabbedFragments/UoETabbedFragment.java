package com.englishtopass.englishtopassapplication.TabbedFragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.englishtopass.englishtopassapplication.Adapters.TabbedUoeRecyclerAdapter;
import com.englishtopass.englishtopassapplication.MainActivityViewModel;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.R;

import java.util.List;


public class UoETabbedFragment extends Fragment {
    private static final String TAG = "UoETabbedFragment";

    private RecyclerView recyclerView;
    private MainActivityViewModel mWordViewModel;


    public UoETabbedFragment() {
        // Required empty public constructor
    }


    public static UoETabbedFragment newInstance() {
        return new UoETabbedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tabbed_uoe_fragment, container, false);

        recyclerView = view.findViewById(R.id.uoeTabbedRecyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final TabbedUoeRecyclerAdapter adapter = new TabbedUoeRecyclerAdapter(getContext(), getActivity());

        recyclerView.setAdapter(adapter);

        mWordViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mWordViewModel.getUseOfEnglishPackageLiveData().observe(this, new Observer<List<UseOfEnglishPackage>>() {
            @Override
            public void onChanged(List<UseOfEnglishPackage> useOfEnglishPackages) {


                adapter.setTabbedList(useOfEnglishPackages);

            }

        });

        return view;

    }

}
