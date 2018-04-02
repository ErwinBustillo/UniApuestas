package com.edu.uninorte.uniapuestas;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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

import com.edu.uninorte.uniapuestas.users.UserEntity;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    UserEntity u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        u = (UserEntity) i.getSerializableExtra("user");
        Log.d("Tagaso Principal", u.toString());

        getSupportActionBar().setSubtitle(DataSingleton.currentUser.getName().toString() + " " + "\n"+ "Points :" + DataSingleton.currentUser.getPoints());
        Log.d("nano", "user points : " +DataSingleton.currentUser.getPoints());

        // NAVEGACION LATERAL
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // FIN DE LA NAVEGACION LATERAL

        setFragment(0);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_verApuestas){
            getSupportActionBar().setTitle("UniApuestas");
            getSupportActionBar().setSubtitle(DataSingleton.currentUser.getName().toString() + " " + "\n"+ "Points :" + DataSingleton.currentUser.getPoints());
            setFragment(0);
        }else if (id == R.id.nav_misApuestas) {
            getSupportActionBar().setTitle("Mis Apuestas");
            getSupportActionBar().setSubtitle(DataSingleton.currentUser.getName().toString() + " " + "\n"+ "Points :" + DataSingleton.currentUser.getPoints());
            setFragment(1);
            // Handle the camera action
        } else if (id == R.id.nav_perfil) {
            getSupportActionBar().setTitle("Mi Perfil");
            getSupportActionBar().setSubtitle(DataSingleton.currentUser.getName().toString() + " " + "\n"+ "Points :" + DataSingleton.currentUser.getPoints());
           setFragment(2);
        }else if (id == R.id.nav_LoadJSON) {
            if (DataSingleton.currentUser.isAdmin()){
                getSupportActionBar().setTitle("Cargar Partidos");
                getSupportActionBar().setSubtitle(DataSingleton.currentUser.getName().toString() + " " + "\n"+ "Points :" + DataSingleton.currentUser.getPoints());
                setFragment(3);
            }
            else{
                Toast.makeText(this,"OPERACION NO PERMITIDA",Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_salir) {
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // funcion para cambiar de fragmentos
    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                AllBetFragment allBetFragment = new AllBetFragment();
                fragmentTransaction.replace(R.id.fragment, allBetFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                MyBetFragment myBetFragment = new MyBetFragment();
                fragmentTransaction.replace(R.id.fragment, myBetFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ProfileFragment profileFragment = new ProfileFragment();
                fragmentTransaction.replace(R.id.fragment, profileFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                LoadJSONFragment loadJSONFragment = new LoadJSONFragment();
                fragmentTransaction.replace(R.id.fragment, loadJSONFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
