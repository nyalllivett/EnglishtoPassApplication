package com.englishtopass.englishtopassapplication.Adapters.PreScreenAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Model.Reading.Questions.Parent.ReadingParent;
import com.englishtopass.englishtopassapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleReadingAdapter extends ListAdapter<ReadingParent, ExampleReadingAdapter.ExampleItemViewHolder> {
    private static final String TAG = "ExamplePageRecyclerView";
    private LayoutInflater layoutInflater;

    public ExampleReadingAdapter(Context context) {
        super(diffUtil);
        this.layoutInflater = LayoutInflater.from(context);

    }

    private static final DiffUtil.ItemCallback<ReadingParent> diffUtil = new DiffUtil.ItemCallback<ReadingParent>() {
        @Override
        public boolean areItemsTheSame(@NonNull ReadingParent oldItem, @NonNull ReadingParent newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ReadingParent oldItem, @NonNull ReadingParent newItem) {
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
        holder.testTime.setText(String.valueOf(getItem(position).getTestTimeElapsed()));
        holder.partType.setText("Mulitple");

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
