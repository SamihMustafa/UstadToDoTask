package com.media.ustadtodo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.media.ustadtodo.data.LoadTasksCallBack;
import com.media.ustadtodo.data.ToDoTask;
import com.media.ustadtodo.data.ToDoTaskRepository;

import java.util.List;

/**
 * Created by Samih on 21-Jun-18.
 */
class MainViewModel extends ViewModel{

    private ToDoTaskRepository toDoTaskRepository;
    private MutableLiveData<List<ToDoTask>> taskList;


    public MainViewModel(ToDoTaskRepository taskrepo){
        toDoTaskRepository = taskrepo;
    }

    public LiveData<List<ToDoTask>> getToDoTaskList(){
        if(taskList == null){
            taskList = new MutableLiveData<>();
            loadTasks();
        }
        return taskList;
    }

    private void loadTasks() {
        toDoTaskRepository.getListOfTasks(new LoadTasksCallBack() {
            @Override
            public void onTasksLoaded(List<ToDoTask> tasks) {
                taskList.setValue(tasks);
            }
        });
    }

    public void addTaskToUi(String title, String desc) {
        toDoTaskRepository.addTask(new ToDoTask(title, desc));
    }
}
