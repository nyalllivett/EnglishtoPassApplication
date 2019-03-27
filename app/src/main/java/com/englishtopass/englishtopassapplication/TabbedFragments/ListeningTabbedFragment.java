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
import com.englishtopass.englishtopassapplication.ViewModels.ListeningViewModel;
import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.R;

import java.util.List;

public class ListeningTabbedFragment extends Fragment {
    private static final String TAG = "ListeningTabbedFragment";

    private ListeningViewModel listeningViewModel;
    private RecyclerView recyclerView;

    public ListeningTabbedFragment() {
        // Required empty public constructor
    }

    public static ListeningTabbedFragment newInstance() {
        return new ListeningTabbedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabbed_listening_fragment, container, false);

        recyclerView = view.findViewById(R.id.listeningTabbedRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        recyclerView.setHasFixedSize(true);

        final TabbedListeningRecyclerAdapter adapter = new TabbedListeningRecyclerAdapter(getContext(), (AppCompatActivity) getActivity());

        recyclerView.setAdapter(adapter);

        listeningViewModel = ViewModelProviders.of(this).get(ListeningViewModel.class);

        listeningViewModel.getListeningPackageLiveData().observe(this, new Observer<List<ListeningPackage>>() {

            @Override
            public void onChanged(List<ListeningPackage> listeningPackages) {

                adapter.submitList(listeningPackages);

            }
        });

        return view;
    }

}
