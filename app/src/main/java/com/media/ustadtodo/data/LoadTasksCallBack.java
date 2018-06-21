package com.media.ustadtodo.data;

import java.util.List;

/**
 * Created by Samih on 21-Jun-18.
 */
public interface LoadTasksCallBack {

    void onTasksLoaded(List<ToDoTask> tasks);
}
