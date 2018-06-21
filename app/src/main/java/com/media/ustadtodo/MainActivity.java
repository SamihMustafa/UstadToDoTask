package com.media.ustadtodo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            MainActivityFragment fragment = new MainActivityFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFrame, fragment, MainActivityFragment.TAG).commit();
        }else{

            MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentByTag(MainActivityFragment.TAG);
            getSupportFragmentManager().beginTransaction().add(fragment,MainActivityFragment.TAG).commit();

        }


    }
}
