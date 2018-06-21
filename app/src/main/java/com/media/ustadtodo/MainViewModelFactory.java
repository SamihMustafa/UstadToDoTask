package com.media.ustadtodo;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.media.ustadtodo.data.ToDoTaskRepository;

/**
 * Created by Samih on 21-Jun-18.
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final ToDoTaskRepository repo;

    public MainViewModelFactory(ToDoTaskRepository toDoTaskRepository){
        repo = toDoTaskRepository;
    }

    @NonNull
    @Override
    public MainViewModel create(@NonNull Class modelClass) {
        return new MainViewModel(repo);
    }
}
