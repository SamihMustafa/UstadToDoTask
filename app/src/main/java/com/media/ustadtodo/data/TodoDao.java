package com.media.ustadtodo.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Samih on 21-Jun-18.
 */
@Dao
public interface TodoDao {

    @Query("SELECT * FROM ToDoTask")
    List<ToDoTask> getAll();

    @Insert
    void insertTask(ToDoTask task);


}
