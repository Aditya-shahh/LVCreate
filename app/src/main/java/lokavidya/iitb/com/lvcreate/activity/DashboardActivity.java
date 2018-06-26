package lokavidya.iitb.com.lvcreate.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lokavidya.iitb.com.lvcreate.R;
import lokavidya.iitb.com.lvcreate.fragment.HomeFragment;
import lokavidya.iitb.com.lvcreate.fragment.NotificationFragment;
import lokavidya.iitb.com.lvcreate.fragment.ProfileFragment;
import lokavidya.iitb.com.lvcreate.fragment.ProjectFragment;
import lokavidya.iitb.com.lvcreate.util.BottomNavigationViewHelper;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Global fields
    FragmentManager fragmentManager;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigation = findViewById(R.id.navigationView);
        // To remove the bubble-like animation
        BottomNavigationViewHelper.removeShiftMode(bottomNavigation);
        // Listener is defined below
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavListener);

        // Set the Frame to HomeFragment
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_frame, new HomeFragment())
                .commit();

        // Boilerplate NavigationDrawer Activity code
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                AlertDialog.Builder mBuilder= new AlertDialog.Builder(DashboardActivity.this);
                View mView= getLayoutInflater().inflate(R.layout.create_dialog,null);

                //bind views from dialog layout
                final EditText projectName = (EditText)mView.findViewById(R.id.projectname);
                Button next = (Button)mView.findViewById(R.id.next);
                mBuilder.setView(mView);
                final AlertDialog dialog=mBuilder.create();


                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!projectName.getText().toString().isEmpty()) {

                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                            String name=projectName.getText().toString();
                            sharedPreferences.edit().putString("ProjectName", name).apply();
                            Toast.makeText(getApplicationContext(), "Project Created Succesfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),ProjectActivity.class);
                            startActivity(i);
                            dialog.dismiss();




                        } else {


                            Toast.makeText(getApplicationContext(), "Enter project name", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                dialog.show();

            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bottom_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.bottom_project:
                            selectedFragment = new ProjectFragment();
                            break;
                        case R.id.bottom_notification:
                            selectedFragment = new NotificationFragment();
                            break;
                        case R.id.bottom_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }

                    fragmentManager.beginTransaction()
                            .replace(R.id.main_frame, selectedFragment)
                            .commit();
                    return true;

                }
            };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
