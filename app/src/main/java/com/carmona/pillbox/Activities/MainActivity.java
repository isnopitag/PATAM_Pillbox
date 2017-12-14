package com.carmona.pillbox.Activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.carmona.pillbox.API.PillboxAPI;
import com.carmona.pillbox.Adapters.NotificacionRVAdapter;
import com.carmona.pillbox.Fragments.AboutFragment;
import com.carmona.pillbox.Fragments.CitasFragment;
import com.carmona.pillbox.Fragments.HomeFragment;
import com.carmona.pillbox.Fragments.NotificacionesFragment;
import com.carmona.pillbox.Models.Notificaciones;
import com.carmona.pillbox.Models.Receta;
import com.carmona.pillbox.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.carmona.pillbox.Config.Preferences.APP_USER;
import static com.carmona.pillbox.Config.Preferences.BASE_URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AboutFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        CitasFragment.OnFragmentInteractionListener,
        NotificacionesFragment.OnFragmentInteractionListener, NotificacionRVAdapter.NotificacionRVAdapterListener,
        Callback<List<Notificaciones>> {

    private FragmentManager fragmentManager;
    private String m_Text;

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
                dialog();

                /*Snackbar.make(view, "Enviar Notificacion...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
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

    public int dialog(){

        int retorno = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enviar Notificacion al Doctor");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Toast.makeText(getBaseContext(),"Enviando Notificacion..."+m_Text, Toast.LENGTH_SHORT).show();
                notificacion();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

        return retorno;
    }

    public void notificacion() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PillboxAPI pillboxAPI = retrofit.create(PillboxAPI.class);
        Call<List<Notificaciones>> call = pillboxAPI.notificacion(""+APP_USER,""+m_Text);
        call.enqueue(this);
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

    @Override
    public void OnItemClicked(Notificaciones aNotificaciones) {

    }

    @Override
    public void onResponse(Call<List<Notificaciones>> call, Response<List<Notificaciones>> response) {

        if(response.isSuccessful()) {
            Toast.makeText(this,"Chido",Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Notificaciones>> call, Throwable t) {

    }
}
