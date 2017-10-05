package com.example.isaachutchinson1.highlanderbuy;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Isaac Hutchinson
 *
 * Login System:
 * Source: https://www.youtube.com/watch?v=6Iy7crsnVhA&list=PLGCjwl1RrtcSi2oV5caEVScjkM6r3HO9t
 */
public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ViewPager mainViewPager;
    private ViewPagerAdaptor tabsViewPagerAdaptor;
    private TabLayout primary_nav_layout;

    /**
     * The onCreate method is the main control for the activity and generates the information and
     * content to the activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mainViewPager = (ViewPager) findViewById(R.id.main_pager);
        tabsViewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager());

        mainViewPager.setAdapter(tabsViewPagerAdaptor);

        primary_nav_layout = (TabLayout) findViewById(R.id.primary_navigation);
        primary_nav_layout.setupWithViewPager(mainViewPager);


    }

    /**
     * The onStart method runs its code when the application starts. This code checks to see if
     * the user has been logged out of the application.
     */
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            mainToStartActivity();
        }

    }

    /**
     * The mainToStartActivity method changes the current activity to another activity.
     */
    private void mainToStartActivity() {
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    /**
     *  The onCreateOptionsMenu creates a menu on the application bar for the user to be able to
     *  logout of the system.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.navigation, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.app_con_logout) {
            FirebaseAuth.getInstance().signOut();
            mainToStartActivity();
        }

        return true;
    }
}
