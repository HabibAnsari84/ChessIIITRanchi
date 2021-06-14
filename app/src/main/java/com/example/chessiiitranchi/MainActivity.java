package com.example.chessiiitranchi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    Toolbar toolbar;

    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navView);
        drawerLayout = findViewById(R.id.drwr_layout);
        navigationView = findViewById(R.id.nav_view);

        appBarConfiguration = new AppBarConfiguration.Builder(new int[]{R.id.matchesFragment, R.id.homeFragment,
                R.id.fixturesFragment,R.id.result,R.id.pointTable,R.id.teams
        }).setDrawerLayout(drawerLayout).build();


        navController = Navigation.findNavController(this, R.id.frame_layout);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.rules){
                    Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.about_dev){
                    Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.adminLogin){
                    Intent intent = new Intent(MainActivity.this, AdminLogin.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.teams){
                    Intent intent = new Intent(MainActivity.this, Teams.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.pointTable){
                    Intent intent = new Intent(MainActivity.this, PointsTable.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.result){
                    Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }



    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


}