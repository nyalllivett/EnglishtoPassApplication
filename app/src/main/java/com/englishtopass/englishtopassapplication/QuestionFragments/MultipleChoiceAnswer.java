package com.englishtopass.englishtopassapplication.QuestionFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MultipleChoiceAnswer extends SpannedQuestion {

    /**
     * partitioned answers is an 3D array
     * providing multiple choices of answers for each
     * span.
     * used at high level fragment
     **/
    protected String[][] partitionedAnswers = new String[8][4];


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    /**
     * this method is called in the OnSuccess of RxJava call
     * to the database retrieving the question.
     * called from low level
     * @param array
     * the 2D array of all answers. wrong and correct.
     * @param partitionSize
     * how many answers i want to display/inside the mcaPartition
     */
    protected void mcaPartition(String[] array, int partitionSize) {

        int partitions = array.length / partitionSize;

        for (int i = 0; i < partitions; i++) {

            partitionedAnswers[i] = Arrays.copyOfRange(array, i * partitionSize, i * partitionSize + partitionSize);

        }
    }


}
