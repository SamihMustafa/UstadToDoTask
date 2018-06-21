package com.media.ustadtodo.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Samih on 21-Jun-18.
 */
@Database(entities = {ToDoTask.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase INSTANCE;

    public abstract TodoDao taskDao();

    private static final Object sLock = new Object();

    public static TaskDatabase getInstance(Context context){
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TaskDatabase.class, "Tasks.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
