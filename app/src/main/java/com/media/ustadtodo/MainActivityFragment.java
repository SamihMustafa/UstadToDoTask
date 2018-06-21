package com.media.ustadtodo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.media.ustadtodo.data.AppExecutor;
import com.media.ustadtodo.data.TaskDatabase;
import com.media.ustadtodo.data.ToDoTask;
import com.media.ustadtodo.data.ToDoTaskRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String TAG = "MainActivityFragment";
    private MainViewModel viewModel;
    private View view;
    private TaskAdapter adapter;
    private MainViewModelFactory factory;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_main, container, false);

        factory = new MainViewModelFactory(ToDoTaskRepository.getInstance(new AppExecutor(), TaskDatabase.getInstance(getContext())));
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);


        setupList();
        setUpObservers();


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openDialog();
            }
        });

        return view;
    }

    private void setUpObservers() {
        viewModel.getToDoTaskList().observe(this, new Observer<List<ToDoTask>>() {
            @Override
            public void onChanged(@Nullable List<ToDoTask> toDoTasks) {
                adapter.setList(toDoTasks);
            }
        });
    }


    private void openDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_task_dialog, null);

        AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        dialog.setTitle("Add ToDo Task");
        dialog.setView(dialogView);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                EditText title = dialogView.findViewById(R.id.editTextTitle);
                EditText desc = dialogView.findViewById(R.id.editTextDesc);

                viewModel.addTaskToUi(title.getText().toString(), desc.getText().toString());
            }
        });
        dialog.show();

    }

    private void setupList() {
        RecyclerView list = view.findViewById(R.id.todolistview);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        list.setHasFixedSize(true);

        adapter = new TaskAdapter(new ArrayList<ToDoTask>());
        list.setAdapter(adapter);
    }

}
