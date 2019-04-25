package com.englishtopass.englishtopassapplication.ViewModels;

import android.app.Application;

import com.englishtopass.englishtopassapplication.Model.Listening.Questions.BlankFillingQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.ListeningOneSituationQuestion;
import com.englishtopass.englishtopassapplication.Model.Listening.Questions.MatchSpeakersQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Package.ReadingPackage;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.GappedTextQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.MatchingExerciseQuestion;
import com.englishtopass.englishtopassapplication.Model.Reading.Questions.MultipleChoiceQuestion;
import com.englishtopass.englishtopassapplication.Repository.QuestionRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Single;

public class ReadingViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;

    private LiveData<List<ReadingPackage>> readingPackageLiveData;

    public ReadingViewModel(@NonNull Application application) {
        super(application);

        questionRepository = new QuestionRepository(application);

        readingPackageLiveData = questionRepository.getAllReadingPackages();

    }

    public LiveData<List<ReadingPackage>> getReadingPackageLiveData() {
        return readingPackageLiveData;
    }

    // RETRIEVE SINGLE READING QUESTION DATA -
    public Single<MultipleChoiceQuestion> getMenuMultipleChoice(int id) {
        return questionRepository.getMenuMulipleChoice(id);
    }

    public Single<GappedTextQuestion> getMenuGappedText(int id) {
        return questionRepository.getMenuGappedText(id);

    }

    public Single<MatchingExerciseQuestion> getMenuMatchingExercise(int id) {
        return questionRepository.getMenuMatchingExercise(id);
    }

}
