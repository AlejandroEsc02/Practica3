package com.example.estadiv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String user, pass;

    private FragmentManager fm;
    private FragmentTransaction ft,fy;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        PreguntasFragment preguntasFragment = new PreguntasFragment();
        ft.replace(R.id.frame, preguntasFragment).commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            //finishAffinity();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch (id) {
            case R.id.cerrarse:
                intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.miperfil:
                Intent intent2=new Intent(MainActivity.this,PerfilActivity.class);
                intent2.putExtra("Username",user);
                intent2.putExtra("Password", pass);
                startActivityForResult(intent2, 12345);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        ft = fm.beginTransaction();


        if (id == R.id.nav_preguntas) {

            PreguntasFragment preguntasFragment = new PreguntasFragment();
            ft.replace(R.id.frame, preguntasFragment).commit();


        } else if (id == R.id.nav_asesores) {

            AsesoresFragment asesoresFragment = new AsesoresFragment();
            ft.replace(R.id.frame, asesoresFragment).commit();

        } else if (id == R.id.nav_perfil) {
            Intent intent2 = new Intent(MainActivity.this, PerfilActivity.class);
            intent2.putExtra("Username", user);
            intent2.putExtra("Password", pass);
            startActivityForResult(intent2, 12345);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void mas(View view) {
        fy = fm.beginTransaction();
        QuestionFragment questionFragment = new QuestionFragment();
        fy.replace(R.id.frame, questionFragment).commit();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12345 && resultCode == RESULT_OK) {
            if (data.getExtras() !=null) {
                user= data.getExtras().getString("Username");
                pass=data.getExtras().getString("Password");

            }
        }

    }
}
