package com.englishtopass.englishtopassapplication.Adapters.TabbedAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.englishtopass.englishtopassapplication.Enums.QuestionType;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.ListeningExampleFragment;
import com.englishtopass.englishtopassapplication.ExampleFragment.ExampleMainScreen.ReadingExampleFragment;
import com.englishtopass.englishtopassapplication.Model.Reading.Package.ReadingPackage;
import com.englishtopass.englishtopassapplication.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import static com.englishtopass.englishtopassapplication.Enums.QuestionType.LISTENING;
import static com.englishtopass.englishtopassapplication.Enums.QuestionType.READING;

public class TabbedReadingRecyclerAdapter extends ListAdapter<ReadingPackage, TabbedReadingRecyclerAdapter.ReadingViewHolder> implements View.OnClickListener {
    private static final String TAG = "TabbedListeningRecycler";

    private LayoutInflater layoutInflater;
    private AppCompatActivity appCompatActivity;
    private ReadingPackage readingPackage;

    public TabbedReadingRecyclerAdapter(Context context, AppCompatActivity appCompatActivity) {
        super(diffUtil);
        layoutInflater = LayoutInflater.from(context);
        this.appCompatActivity = appCompatActivity;

    }

    public static final DiffUtil.ItemCallback<ReadingPackage> diffUtil = new DiffUtil.ItemCallback<ReadingPackage>() {
        @Override
        public boolean areItemsTheSame(@NonNull ReadingPackage oldItem, @NonNull ReadingPackage newItem) {
            return oldItem.getId() == newItem.getId();

        }

        @Override
        public boolean areContentsTheSame(@NonNull ReadingPackage oldItem, @NonNull ReadingPackage newItem) {
            return oldItem.getTestCompletion() == newItem.getTestCompletion() ||
                    oldItem.getTestTimeElapsed() == newItem.getTestTimeElapsed();
        }
    };


    @NonNull
    @Override
    public ReadingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.tabbed_uoe_item, parent, false);

        return new ReadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingViewHolder holder, int position) {


            readingPackage = getItem(position);

            holder.startListeningQuestionButton.setOnClickListener(this);

            holder.startListeningQuestionButton.setTag(readingPackage.getId());

            holder.readingTestNumberTextView.setText(String.format("Test Number %s", readingPackage.getId()));

            holder.multipleChoiceTitle.setText(String.format("• %s", readingPackage.getMultipleChoiceTitle()));

            holder.gappedTextTitle.setText(String.format("• %s", readingPackage.getGappedTextTitle()));

            holder.matchingExerciseTitle.setText(String.format("• %s", readingPackage.getMatchingExerciseTitle()));


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.startUoeQuestionButton:

                FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager()
                        .beginTransaction();

                transaction.add(R.id.questionFragmentHolder, ReadingExampleFragment.newInstance(readingPackage.getTestCompletion(), (Integer) v.getTag()), "listeningExampleFragment")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack("listeningExampleFragment")
                        .commit();

                break;
        }

    }

    class ReadingViewHolder extends RecyclerView.ViewHolder {

        ImageView startListeningQuestionButton;
        TextView readingTestNumberTextView;
        TextView multipleChoiceTitle;
        TextView gappedTextTitle;
        TextView matchingExerciseTitle;

        public ReadingViewHolder(@NonNull View itemView) {
            super(itemView);

            // REUSING THE UOE RECYCLER VIEW ITEM SO ID DO NOT CORRESPOND WITH READING -
            startListeningQuestionButton = itemView.findViewById(R.id.startUoeQuestionButton);
            readingTestNumberTextView = itemView.findViewById(R.id.uoeTestNumberTextView);
            multipleChoiceTitle = itemView.findViewById(R.id.multipleChoiceClozeTitleTextView);
            gappedTextTitle = itemView.findViewById(R.id.openClozeTitleTextView);
            matchingExerciseTitle = itemView.findViewById(R.id.keywordTransformationTitleTextView);
        }
    }

}
