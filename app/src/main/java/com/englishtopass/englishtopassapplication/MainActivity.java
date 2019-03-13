package com.englishtopass.englishtopassapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.englishtopass.englishtopassapplication.Adapters.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;



// TODO: 05/02/2019 allow back up true -
public class MainActivity extends AppCompatActivity implements OnBackPressedCallback {
    private static final String TAG = "MainActivity";

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Setting it as the action bar -
        setSupportActionBar(toolbar);

        // This adapter finds which tab is selected by id then creates the fragment and passes the ID + 1 -
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Initializing the view that swipes -
        mViewPager = findViewById(R.id.container);

        // Setting the adapter to the view pager, the adapter will then create the fragment with the id of the tab -
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initializing the tabLayout -
        TabLayout tabLayout = findViewById(R.id.tabs);

        // Not sure what these do yet -
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // FLOATING ACTION BUTTON -
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Log.d(TAG, "onClick: " + mSectionsPagerAdapter.getCount());
            }
        });

    }

    /**
     * MENU
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Log.d(TAG, "onOptionsItemSelected: hello");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean handleOnBackPressed() {

        Log.d(TAG, "handleOnBackPressed: main activity");

        return true;
    }
}
