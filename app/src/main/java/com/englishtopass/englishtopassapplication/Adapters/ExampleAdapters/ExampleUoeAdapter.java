package com.englishtopass.englishtopassapplication.Adapters.ExampleAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.UoeParent;
import com.englishtopass.englishtopassapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleUoeAdapter extends ListAdapter<UoeParent, ExampleUoeAdapter.ExampleItemViewHolder> {
    private static final String TAG = "ExamplePageRecyclerView";
    private LayoutInflater layoutInflater;

    public ExampleUoeAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.layoutInflater = LayoutInflater.from(context);

    }

    private static final DiffUtil.ItemCallback<UoeParent> DIFF_CALLBACK = new DiffUtil.ItemCallback<UoeParent>() {
        @Override
        public boolean areItemsTheSame(@NonNull UoeParent oldItem, @NonNull UoeParent newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UoeParent oldItem, @NonNull UoeParent newItem) {

             return oldItem.getTestTimeElapsed() == (newItem.getTestTimeElapsed());

        }
    };

    @NonNull
    @Override
    public ExampleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.example_question_item, parent, false);

        return new ExampleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleItemViewHolder holder, int position) {

        holder.partTitle.setText(getItem(position).getTitle());
        holder.testTime.setText(getItem(position).timeElapsedToString());

        String questionType = "";

//        switch (getItem(position).getPartUoe()) {
//
//            case WORD_FORMATION:
//                questionType = "Word Formation";
//                break;
//
//            case KEYWORD_TRANSFORMATION:
//                questionType = "Keyword Transformation";
//                break;
//
//            case OPEN_CLOZE:
//                questionType = "Open Cloze";
//                break;
//
//            case MULTIPLE_CHOICE_CLOZE:
//                questionType = "Multiple Choice Cloze";
//                break;
//
//        }

        holder.partType.setText(questionType);

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

}
