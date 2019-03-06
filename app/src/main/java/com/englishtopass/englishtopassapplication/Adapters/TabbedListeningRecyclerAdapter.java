package com.englishtopass.englishtopassapplication.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.R;
import com.englishtopass.englishtopassapplication.UoeExampleFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class TabbedListeningRecyclerAdapter extends RecyclerView.Adapter<TabbedListeningRecyclerAdapter.ListeningViewHolder> implements View.OnClickListener {
    private static final String TAG = "TabbedListeningRecycler";

    private List<ListeningPackage> listeningPackages = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private AppCompatActivity appCompatActivity;

    public TabbedListeningRecyclerAdapter(Context context, AppCompatActivity appCompatActivity) {

        layoutInflater = LayoutInflater.from(context);

        this.appCompatActivity = appCompatActivity;

    }

    @NonNull
    @Override
    public ListeningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.tabbed_uoe_item, parent, false);

        return new ListeningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeningViewHolder holder, int position) {

        if (listeningPackages != null) {

            ListeningPackage listeningPackage = listeningPackages.get(position);

            holder.startListeningQuestionButton.setOnClickListener(this);

            holder.startListeningQuestionButton.setTag(listeningPackage.getId());

            holder.listeningTestNumberTextView.setText(String.format("Test Number %s", listeningPackage.getId()));

            holder.listeningMultipleSituationTitle.setText(String.format("• %s", listeningPackage.getListeningMultipleSituationsQuestion().getQuestionTitle()));

            holder.blankFillingTitle.setText(String.format("• %s", listeningPackage.getBlankFillingQuestion().getQuestionTitle()));

            holder.matchSpeakersTitle.setText(String.format("• %s", listeningPackage.getMatchSpeakersQuestion().getQuestionTitle()));

            holder.listeningOneSituationTitle.setText(String.format("• %s", listeningPackage.getListeningOneSituationQuestion().getQuestionTitle()));


        } else {

            // Setting the text views to loading if the database transaction hasn't completed,
            // Once it has it will trigger the observer and set the text views

            holder.listeningTestNumberTextView.setText("Loading");

            holder.listeningMultipleSituationTitle.setText("Loading");

            holder.blankFillingTitle.setText("Loading");

            holder.matchSpeakersTitle.setText("Loading");

            holder.listeningOneSituationTitle.setText("Loading");

        }

    }

    @Override
    public int getItemCount() {

        if (listeningPackages != null) {

            return listeningPackages.size();

        } else {

            return 0;

        }

    }


    public void setTabbedList(List<ListeningPackage> arrayList){

        this.listeningPackages = arrayList;

        notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.startUoeQuestionButton:

                FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager()
                        .beginTransaction();

                transaction.add(R.id.questionFragmentHolder, UoeExampleFragment.newInstance(2), "listeningExampleFragment")
                        .addToBackStack("listeningExampleFragment")
                        .commit();

                break;
        }

    }

    class ListeningViewHolder extends RecyclerView.ViewHolder {

        ImageView startListeningQuestionButton;
        TextView listeningTestNumberTextView;
        TextView listeningMultipleSituationTitle;
        TextView blankFillingTitle;
        TextView matchSpeakersTitle;
        TextView listeningOneSituationTitle;

        public ListeningViewHolder(@NonNull View itemView) {
            super(itemView);

            // REUSING THE UOE RECYCLER VIEW ITEM SO ID DO NOT CORRESPOND WITH LISTENING -
            startListeningQuestionButton = itemView.findViewById(R.id.startUoeQuestionButton);
            listeningTestNumberTextView = itemView.findViewById(R.id.uoeTestNumberTextView);
            listeningMultipleSituationTitle = itemView.findViewById(R.id.multipleChoiceClozeTitleTextView);
            blankFillingTitle = itemView.findViewById(R.id.openClozeTitleTextView);
            matchSpeakersTitle = itemView.findViewById(R.id.keywordTransformationTitleTextView);
            listeningOneSituationTitle = itemView.findViewById(R.id.wordFormationTitleTextView);
        }
    }

}
