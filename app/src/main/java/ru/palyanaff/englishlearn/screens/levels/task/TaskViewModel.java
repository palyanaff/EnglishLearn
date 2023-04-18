package ru.palyanaff.englishlearn.screens.levels.task;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TaskViewModel extends ViewModel{
    //private MutableLiveData<List<String>> tasks;
    private String currentWord;

    public TaskViewModel(String currentWord) {
        this.currentWord = currentWord;
    }
}
