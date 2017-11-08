package com.jesusramirez.proyecto.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jesusramirez.proyecto.BaseDeDatos.CRUD_Tablas;
import com.jesusramirez.proyecto.Modelos.Grupos;
import com.jesusramirez.proyecto.R;
import com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroCrearGrupoFragment;
import com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroGrupoActualFragment;
import com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroGruposFragment;

import static com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroCrearGrupoFragment.capacidad;
import static com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroCrearGrupoFragment.diasClase;
import static com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroCrearGrupoFragment.faltasPermitidas;
import static com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroCrearGrupoFragment.horaSeleccionada;
import static com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroCrearGrupoFragment.nombreMateria;
import static com.jesusramirez.proyecto.views.fragmentsMaestro.MaestroCrearGrupoFragment.salon;

public class HomeMaestro extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MaestroGruposFragment.OnFragmentInteractionListener,
        MaestroCrearGrupoFragment.OnFragmentInteractionListener, MaestroGrupoActualFragment.OnFragmentInteractionListener {

    MaestroGruposFragment fragmentGrupo;
    MaestroCrearGrupoFragment fragmentCrearGrupo;
    MaestroGrupoActualFragment fragmentGrupoActual;
    CRUD_Tablas datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_maestro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Instanciar fragments
        fragmentGrupo = new MaestroGruposFragment();
        fragmentCrearGrupo = new MaestroCrearGrupoFragment();
        fragmentGrupoActual = new MaestroGrupoActualFragment();

        Toolbar barra=(Toolbar)findViewById(R.id.toolbar);
        barra.setTitle("Grupos");
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentsMaestro,fragmentGrupo).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            moveTaskToBack(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_maestro, menu);
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

        if (id == R.id.nav_grupos) {
            Toolbar barra=(Toolbar)findViewById(R.id.toolbar);
            barra.setTitle("Grupos");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedorFragmentsMaestro,fragmentGrupo).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null).commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_cerrarsesionM) {
            cerrarToken();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cerrarToken(){
        SharedPreferences tokenSesion = getSharedPreferences("TokenSesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenSesion.edit();
        editor.putString("SesionIniciada","false");
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        editor.commit();
    }

    public void agregarGrupo(View view){
        Toolbar barra=(Toolbar)findViewById(R.id.toolbar);
        barra.setTitle("Crear Grupo");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragmentsMaestro,fragmentCrearGrupo).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    public void grupoSeleccionado(View view){
        Toolbar barra=(Toolbar)findViewById(R.id.toolbar);
        barra.setTitle("Grupo Actual");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragmentsMaestro,fragmentGrupoActual).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class tareaAsyncNuevoGrupo extends AsyncTask<Void,Void,Void> {
        MaestroCrearGrupoFragment nuevoGrupo = null;

        @Override
        protected Void doInBackground(Void... voids) {
            nuevoGrupo.diasDeClase();
            try {
                datos.getDb().beginTransaction();
                datos.insertarGrupo(new Grupos(null,
                        null,
                        nombreMateria.getText().toString(),
                        salon.getText().toString(),
                        horaSeleccionada,
                        Integer.parseInt(capacidad.getText().toString()),
                        diasClase,
                        Integer.parseInt(faltasPermitidas.getText().toString())));
                Toast.makeText(getApplicationContext(),"Grupo Creado",Toast.LENGTH_SHORT).show();
                datos.getDb().setTransactionSuccessful();
            }finally {
                datos.getDb().endTransaction();
            }
            return null;
        }
    }

}
