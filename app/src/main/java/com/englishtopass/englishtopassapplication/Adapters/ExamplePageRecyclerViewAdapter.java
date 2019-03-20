package com.englishtopass.englishtopassapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Model.Listening.Package.ListeningPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Package.UseOfEnglishPackage;
import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;
import com.englishtopass.englishtopassapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExamplePageRecyclerViewAdapter extends RecyclerView.Adapter<ExamplePageRecyclerViewAdapter.ExampleItemViewHolder> {

    private Context context;
    private UseOfEnglishPackage useOfEnglishPackage;
    private ListeningPackage listeningPackage;
    private List<ModelUoeParent> parts;
    private LayoutInflater layoutInflater;


    public ExamplePageRecyclerViewAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

        parts = new ArrayList<>();

    }

    @NonNull
    @Override
    public ExampleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.example_question_item, parent, false);

        return new ExampleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleItemViewHolder holder, int position) {

        holder.partTitle.setText(parts.get(position).getTitle());
        holder.testTime.setText("--:--");
        holder.partType.setText("Mulitple");

    }

    @Override
    public int getItemCount() {
        return parts.size();
    }

    class ExampleItemViewHolder extends RecyclerView.ViewHolder {
        TextView partType;
        TextView partTitle;
        TextView testTime;
        ImageView progressBall;


        public ExampleItemViewHolder(@NonNull View itemView) {
            super(itemView);

            partType = itemView.findViewById(R.id.partType);
            partTitle = itemView.findViewById(R.id.partTitle);
            testTime = itemView.findViewById(R.id.currentTestTime);
            progressBall = itemView.findViewById(R.id.partProgressBall);

        }
    }

    public void setAdapter(UseOfEnglishPackage useOfEnglishPackage) {

        clearParts();

        this.useOfEnglishPackage = useOfEnglishPackage;

        parts.add(useOfEnglishPackage.getMultipleChoiceClozeQuestion());
        parts.add(useOfEnglishPackage.getOpenClozeQuestion());
        parts.add(useOfEnglishPackage.getKeywordTransformationQuestion());
        parts.add(useOfEnglishPackage.getWordFormationQuestion());

        notifyDataSetChanged();

    }


    public void setAdapter(ListeningPackage listeningPackage) {

        this.listeningPackage = listeningPackage;

        parts.add(listeningPackage.getListeningMultipleSituationsQuestion());
        parts.add(listeningPackage.getBlankFillingQuestion());
        parts.add(listeningPackage.getMatchSpeakersQuestion());
        parts.add(listeningPackage.getListeningOneSituationQuestion());

        notifyDataSetChanged();

    }

    private void clearParts() {

        parts.clear();

    }


}
