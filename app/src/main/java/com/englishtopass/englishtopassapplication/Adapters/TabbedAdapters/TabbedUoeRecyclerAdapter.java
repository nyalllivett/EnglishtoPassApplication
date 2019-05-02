package com.englishtopass.englishtopassapplication.Adapters.TabbedAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.PreQuestionFragment.PreQuestionMainScreen.PreUoeScreen;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class TabbedUoeRecyclerAdapter extends ListAdapter<UseOfEnglishPackage, TabbedUoeRecyclerAdapter.UseOfEnglishViewHolder> implements View.OnClickListener {
    private static final String TAG = "TabbedUoeAdapter";

    private LayoutInflater mInflater;
    private AppCompatActivity activity;
    private UseOfEnglishPackage useOfEnglishPackage;

    public TabbedUoeRecyclerAdapter(Context context, Activity activity) {
        super(diffUtil);
        this.mInflater = LayoutInflater.from(context);
        this.activity = (AppCompatActivity) activity;
    }

    private static final DiffUtil.ItemCallback<UseOfEnglishPackage> diffUtil = new DiffUtil.ItemCallback<UseOfEnglishPackage>() {
        @Override
        public boolean areItemsTheSame(@NonNull UseOfEnglishPackage oldItem, @NonNull UseOfEnglishPackage newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UseOfEnglishPackage oldItem, @NonNull UseOfEnglishPackage newItem) {
            return oldItem.getTestCompletion() == newItem.getTestCompletion() ||
                    oldItem.getTestTimeElapsed() == newItem.getTestTimeElapsed();
        }
    };


    @NonNull
    @Override
    public UseOfEnglishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.main_menu_4_item, parent, false);

        return new UseOfEnglishViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UseOfEnglishViewHolder holder, int position) {

            useOfEnglishPackage = getItem(position);

            holder.startUoeQuestionButton.setOnClickListener(this);

            holder.startUoeQuestionButton.setTag(useOfEnglishPackage.getId());

            holder.uoeTestNumberTextView.setText(String.format("Test Number %s", getItem(position).getId()));

            holder.multipleChoiceClozeTitle.setText(String.format("• %s", useOfEnglishPackage.getMultipleChoiceClozeTitle()));

            holder.openClozeTitle.setText(String.format("• %s", useOfEnglishPackage.getOpenClozeTitle()));

            holder.keywordTransformationTitle.setText(String.format("• %s", useOfEnglishPackage.getKeywordTransformationTitle()));

            holder.wordFormationTitle.setText(String.format("• %s", useOfEnglishPackage.getWordFormationTitle()));



    }

    @Override
    public void onClick(View v) {

        FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(R.id.questionFragmentHolder, PreUoeScreen.newInstance(useOfEnglishPackage.getTestCompletion(), (Integer) v.getTag()), "uoeExampleFragment")
                .addToBackStack("uoeExampleFragment")
                .commit();

    }

    // INNER CLASS
    class UseOfEnglishViewHolder extends RecyclerView.ViewHolder{

        ImageView startUoeQuestionButton;
        TextView uoeTestNumberTextView;
        TextView multipleChoiceClozeTitle;
        TextView openClozeTitle;
        TextView keywordTransformationTitle;
        TextView wordFormationTitle;

        // CONSTRUCTOR
        UseOfEnglishViewHolder(@NonNull View itemView) {

            super(itemView);

            startUoeQuestionButton = itemView.findViewById(R.id.startUoeQuestionButton);
            uoeTestNumberTextView = itemView.findViewById(R.id.uoeTestNumberTextView);
            multipleChoiceClozeTitle = itemView.findViewById(R.id.multipleChoiceClozeTitleTextView);
            openClozeTitle = itemView.findViewById(R.id.openClozeTitleTextView);
            keywordTransformationTitle = itemView.findViewById(R.id.keywordTransformationTitleTextView);
            wordFormationTitle = itemView.findViewById(R.id.wordFormationTitleTextView);


        }

    }


}
