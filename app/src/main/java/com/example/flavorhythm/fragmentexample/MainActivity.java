package com.example.flavorhythm.fragmentexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Displays a static fragment by declaring one in XML layout.
 * FAButton starts new SubActivity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Sets up ActionBar and FAButton when Activity is created
     * @param savedInstanceState Saved Activity state. Ignored for this application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Sets toolbar as custom Actionbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Starts SubActivity when FAButton is clicked
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SubActivity.class));
            }
        });
    }

    /**
     * Creates Options menu within Actionbar
     * @param menu Menu object parameter used for ActionBar menu creation.
     * @return returns false to prevent menu creation.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {return false;}
}
