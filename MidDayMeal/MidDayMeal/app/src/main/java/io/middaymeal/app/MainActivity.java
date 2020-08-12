package io.middaymeal.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    CardView cvAttendance,cvBudgetInfo,cvMenu,cvEnrol;
    View navViewl;
    TextView name,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navViewl = navigationView.getHeaderView(0);
        findViewById();
    }

    private void findViewById() {
        cvAttendance=(CardView)findViewById(R.id.cvAttendance);
        cvBudgetInfo=(CardView)findViewById(R.id.cvBudgetInfo);
        cvMenu=(CardView)findViewById(R.id.cvMenu);
        cvEnrol=(CardView)findViewById(R.id.cvEnrol);

        name= (TextView)navViewl.findViewById(R.id.name);
        id= (TextView)navViewl.findViewById(R.id.textView);
        name.setText(Utility.getPreferences(this,Constants.first_name) + " "+Utility.getPreferences(this,Constants.last_name) );
        id.setText(Utility.getPreferences(this,Constants.keyUserName));
        cvAttendance.setOnClickListener(this);
        cvBudgetInfo.setOnClickListener(this);
        cvMenu.setOnClickListener(this);
        cvEnrol.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(this,EnrollmentActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this,ActivityClass.class));
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this,BudgetInfo.class));
        } else if (id == R.id.nav_logout) {
            Utility.clearPreferenceData(this);
            startActivity(new Intent(this,LoginActivity.class));
        } else if (id == R.id.nav_menu) {
            startActivity(new Intent(this,Menu.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.cvAttendance:
                startActivity(new Intent(this,ActivityClass.class));
                break;

            case R.id.cvBudgetInfo:
                startActivity(new Intent(this,BudgetInfo.class));
                break;
            case R.id.cvMenu:
                startActivity(new Intent(this, Menu.class));
                break;
            case R.id.cvEnrol:
                startActivity(new Intent(this, EnrollmentActivity.class));
                break;
        }
    }
}
