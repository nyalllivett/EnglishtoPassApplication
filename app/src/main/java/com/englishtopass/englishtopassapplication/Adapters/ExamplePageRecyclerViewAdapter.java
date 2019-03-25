package com.englishtopass.englishtopassapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Model.UseOfEnglish.Question.Parent.ModelUoeParent;
import com.englishtopass.englishtopassapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ExamplePageRecyclerViewAdapter extends ListAdapter<ModelUoeParent,ExamplePageRecyclerViewAdapter.ExampleItemViewHolder> {
    private static final String TAG = "ExamplePageRecyclerView";
    private LayoutInflater layoutInflater;

    public ExamplePageRecyclerViewAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.layoutInflater = LayoutInflater.from(context);

    }

    private static final DiffUtil.ItemCallback<ModelUoeParent> DIFF_CALLBACK = new DiffUtil.ItemCallback<ModelUoeParent>() {
        @Override
        public boolean areItemsTheSame(@NonNull ModelUoeParent oldItem, @NonNull ModelUoeParent newItem) {
            return false;

        }

        @Override
        public boolean areContentsTheSame(@NonNull ModelUoeParent oldItem, @NonNull ModelUoeParent newItem) {

             return oldItem.getTimeElapsed() == (newItem.getTimeElapsed());

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
        holder.testTime.setText(String.valueOf(getItem(position).getTimeElapsed()));
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
