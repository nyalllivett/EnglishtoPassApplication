package com.englishtopass.englishtopassapplication.Adapters.TabbedAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen.PreListeningScreen;
import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class TabbedListeningRecyclerAdapter extends ListAdapter<ListeningPackage, TabbedListeningRecyclerAdapter.ListeningViewHolder> implements View.OnClickListener {
    private static final String TAG = "TabbedListeningRecycler";

    private LayoutInflater layoutInflater;
    private AppCompatActivity appCompatActivity;
    private ListeningPackage listeningPackage;

    public TabbedListeningRecyclerAdapter(Context context, AppCompatActivity appCompatActivity) {
        super(diffUtil);
        layoutInflater = LayoutInflater.from(context);
        this.appCompatActivity = appCompatActivity;
    }

    private static final DiffUtil.ItemCallback<ListeningPackage> diffUtil = new DiffUtil.ItemCallback<ListeningPackage>() {
        @Override
        public boolean areItemsTheSame(@NonNull ListeningPackage oldItem, @NonNull ListeningPackage newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListeningPackage oldItem, @NonNull ListeningPackage newItem) {
            return oldItem.getTestCompletion() == newItem.getTestCompletion() ||
                    oldItem.getTestTimeElapsed() == newItem.getTestTimeElapsed();
        }
    };

    @NonNull
    @Override
    public ListeningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.main_menu_4_item, parent, false);

        return new ListeningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeningViewHolder holder, int position) {


            listeningPackage = getItem(position);

            holder.startListeningQuestionButton.setOnClickListener(this);

            holder.startListeningQuestionButton.setTag(listeningPackage.getId());

            holder.listeningTestNumberTextView.setText(String.format("Test Number %s", listeningPackage.getId()));

            holder.listeningMultipleSituationTitle.setText(String.format("• %s", listeningPackage.getListeningMultipleSituationsTitle()));

            holder.blankFillingTitle.setText(String.format("• %s", listeningPackage.getBlankFillingTitle()));

            holder.matchSpeakersTitle.setText(String.format("• %s", listeningPackage.getMatchSpeakersTitle()));

            holder.listeningOneSituationTitle.setText(String.format("• %s", listeningPackage.getListeningOneSituationTitle()));


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.startUoeQuestionButton:

                FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager()
                        .beginTransaction();

                transaction.add(R.id.questionFragmentHolder, PreListeningScreen.newInstance((Integer) v.getTag()), "listeningExampleFragment")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
