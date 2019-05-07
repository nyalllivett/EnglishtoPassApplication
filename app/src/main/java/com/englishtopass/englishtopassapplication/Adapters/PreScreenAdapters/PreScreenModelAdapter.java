package com.englishtopass.englishtopassapplication.Adapters.PreScreenAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Interfaces.OnRecyclerClick;
import com.englishtopass.englishtopassapplication.Model.ModelParent;
import com.englishtopass.englishtopassapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PreScreenModelAdapter extends ListAdapter<ModelParent, PreScreenModelAdapter.ExampleItemViewHolder> {
    private LayoutInflater layoutInflater;
    private OnRecyclerClick onRecyclerClick;

    public PreScreenModelAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.layoutInflater = LayoutInflater.from(context);
    }

    private static final DiffUtil.ItemCallback<ModelParent> DIFF_CALLBACK = new DiffUtil.ItemCallback<ModelParent>() {
        @Override
        public boolean areItemsTheSame(@NonNull ModelParent oldItem, @NonNull ModelParent newItem) {
            return true;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ModelParent oldItem, @NonNull ModelParent newItem) {
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
        holder.partType.setText(getItem(position).getType());
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
            itemView.setOnClickListener(v -> onRecyclerClick.onItemClick(getAdapterPosition()));

        }
    }

    public void setOnRecyclerClick(OnRecyclerClick onRecyclerClick) {
        this.onRecyclerClick = onRecyclerClick;
    }

}
