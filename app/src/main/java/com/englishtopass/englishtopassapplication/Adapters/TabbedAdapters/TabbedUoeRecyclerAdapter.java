package com.englishtopass.englishtopassapplication.Adapters.TabbedAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.UoeExampleFragment;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Enums.QuestionType;
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



    public TabbedUoeRecyclerAdapter(Context context, Activity activity) {
        super(diffUtil);
        this.mInflater = LayoutInflater.from(context);
        this.activity = (AppCompatActivity) activity;
    }

    public static final DiffUtil.ItemCallback<UseOfEnglishPackage> diffUtil = new DiffUtil.ItemCallback<UseOfEnglishPackage>() {
        @Override
        public boolean areItemsTheSame(@NonNull UseOfEnglishPackage oldItem, @NonNull UseOfEnglishPackage newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UseOfEnglishPackage oldItem, @NonNull UseOfEnglishPackage newItem) {
            return oldItem.getTestCompletion() == newItem.getTestCompletion();
        }
    };


    @NonNull
    @Override
    public UseOfEnglishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.tabbed_uoe_item, parent, false);

        return new UseOfEnglishViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UseOfEnglishViewHolder holder, int position) {

            UseOfEnglishPackage useOfEnglishPackage = getItem(position);

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

        transaction.add(R.id.questionFragmentHolder, UoeExampleFragment.newInstance(QuestionType.USE_OF_ENGLISH, (Integer) v.getTag()), "uoeExampleFragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
