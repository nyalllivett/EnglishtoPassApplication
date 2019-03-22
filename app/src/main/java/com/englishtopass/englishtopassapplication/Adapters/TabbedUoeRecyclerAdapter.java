package com.englishtopass.englishtopassapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.UoeExampleFragment;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.QuestionType;
import com.englishtopass.englishtopassapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class TabbedUoeRecyclerAdapter extends RecyclerView.Adapter<TabbedUoeRecyclerAdapter.UseOfEnglishViewHolder> implements View.OnClickListener {
    private static final String TAG = "TabbedUoeAdapter";

    private List<UseOfEnglishPackage> useOfEnglishPackages = new ArrayList<>();
    private LayoutInflater mInflater;
    private AppCompatActivity activity;

    public TabbedUoeRecyclerAdapter(Context context, Activity activity) {
        mInflater = LayoutInflater.from(context);
        this.activity = (AppCompatActivity) activity;
    }


    @NonNull
    @Override
    public UseOfEnglishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.tabbed_uoe_item, parent, false);

        return new UseOfEnglishViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UseOfEnglishViewHolder holder, int position) {

        if (useOfEnglishPackages != null) {

            UseOfEnglishPackage useOfEnglishPackage = useOfEnglishPackages.get(position);

            holder.startUoeQuestionButton.setOnClickListener(this);

            holder.startUoeQuestionButton.setTag(useOfEnglishPackage.getId());

            holder.uoeTestNumberTextView.setText(String.format("Test Number %s", useOfEnglishPackage.getId()));

//            holder.multipleChoiceClozeTitle.setText(String.format("• %s", useOfEnglishPackage.getMultipleChoiceClozeQuestion().getTitle()));
//
//            holder.openClozeTitle.setText(String.format("• %s", useOfEnglishPackage.getOpenClozeQuestion().getTitle()));
//
//            holder.keywordTransformationTitle.setText(String.format("• %s", useOfEnglishPackage.getKeywordTransformationQuestion().getTitle()));
//
//            holder.wordFormationTitle.setText(String.format("• %s", useOfEnglishPackage.getWordFormationQuestion().getTitle()));


        } else {

            // Setting the text views to loading if the database transaction hasn't completed,
            // Once it has it will trigger the observer and set the text views

            holder.uoeTestNumberTextView.setText("Loading");

            holder.multipleChoiceClozeTitle.setText("Loading");

            holder.openClozeTitle.setText("Loading");

            holder.keywordTransformationTitle.setText("Loading");

            holder.wordFormationTitle.setText("Loading");

        }

    }

    @Override
    public int getItemCount() {
        return useOfEnglishPackages.size();
    }

    @Override
    public void onClick(View v) {

        FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();

        transaction.add(R.id.questionFragmentHolder, UoeExampleFragment.newInstance(QuestionType.USE_OF_ENGLISH, (Integer) v.getTag(), 4), "uoeExampleFragment")
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

    /**
     * Resetting the live data when it changes
     */
    public void setTabbedList(List<UseOfEnglishPackage> useOfEnglishPackages){

        this.useOfEnglishPackages = useOfEnglishPackages;

        notifyDataSetChanged();

    }

}
