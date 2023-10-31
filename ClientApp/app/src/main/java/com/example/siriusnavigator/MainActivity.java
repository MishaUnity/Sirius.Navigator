package com.example.siriusnavigator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity
{

    private SearchView searchField;

    private FrameLayout searchMenuLayout;
    private Animation searchMenuIn, searchMenuOut;

    public boolean SearchMenuOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.Search_field);

        searchMenuLayout = findViewById(R.id.Search_Menu_Layout);
        searchMenuIn = AnimationUtils.loadAnimation(this, R.anim.appearance_from_up);
        searchMenuOut = AnimationUtils.loadAnimation(this, R.anim.disappearance_to_up);

        searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                if(query.length() != 0)
                     OpenSearchMenu();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });

        searchField.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                CloseSearchMenu();
                return false;
            }
        });
    }

    private void OpenSearchMenu()
    {
        if (SearchMenuOpened)
            return;
        SearchMenuOpened = true;

        searchMenuLayout.setActivated(true);
        searchMenuLayout.setVisibility(View.VISIBLE);

        searchMenuLayout.startAnimation(searchMenuIn);
    }

    private void CloseSearchMenu()
    {
        if (!SearchMenuOpened)
            return;
        SearchMenuOpened = false;

        searchMenuLayout.startAnimation(searchMenuOut);
        searchMenuLayout.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                searchMenuLayout.setActivated(false);
                searchMenuLayout.setVisibility(View.INVISIBLE);
            }
        }, 600);
    }
}