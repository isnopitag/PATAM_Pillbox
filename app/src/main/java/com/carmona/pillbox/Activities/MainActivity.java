package com.carmona.pillbox.Activities;

import android.app.Fragment;
import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
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

import com.carmona.pillbox.Fragments.AboutFragment;
import com.carmona.pillbox.Fragments.CitasFragment;
import com.carmona.pillbox.Fragments.HomeFragment;
import com.carmona.pillbox.Fragments.NotificacionesFragment;
import com.carmona.pillbox.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AboutFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        CitasFragment.OnFragmentInteractionListener,
        NotificacionesFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        fragmentManager = getFragmentManager();
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Enviar Notificacion...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        HomeFragment hf = new HomeFragment();
        verFragment(hf);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_home) {
            HomeFragment hf = new HomeFragment();
            verFragment(hf);
        } else if (id == R.id.nav_citas) {
            CitasFragment cf = new CitasFragment();
            verFragment(cf);
        } else if (id == R.id.nav_notificacion) {
            NotificacionesFragment nf = new NotificacionesFragment();
            verFragment(nf);
        } else if (id == R.id.nav_contacto) {
            AboutFragment af = new AboutFragment();
            verFragment(af);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*public void verFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.Contendor,fragment);
        ft.commit();
    }*/

    private void verFragment(Fragment fragment) {
        if (fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()) != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.Contendor, fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()), fragment.getClass().getSimpleName())
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.Contendor, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
        //updateMenu(fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
