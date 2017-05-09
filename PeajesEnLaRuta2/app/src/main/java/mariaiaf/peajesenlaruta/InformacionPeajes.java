package mariaiaf.peajesenlaruta;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InformacionPeajes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static Activity activity;
    ManejadorBaseDeDatos manejadorBD;
    TextView nombrePeaje, ensayo,ensayo2;
    String datoNombre;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ManejadorDeDatos.ObtenerListaValorCategoria().clear();
        ManejadorDeDatos.ObtenerListaInformacion().clear();
        ManejadorDeDatos.ObtenerListaLatitud().clear();
        ManejadorDeDatos.ObtenerListaLongitud().clear();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_informacion_peajes);
        activity = this;
        manejadorBD = ManejadorBaseDeDatos.instance();
        nombrePeaje=(TextView)findViewById(R.id.tv_nombre1);
        //ensayo=(TextView)findViewById(R.id.textView4);
        //ensayo2=(TextView)findViewById(R.id.textView5);
        Intent intencion=getIntent();
        Bundle extras=intencion.getExtras();
        if(extras!=null)
        {
            datoNombre=extras.getString("Nombre_Peaje");
            nombrePeaje.setText(datoNombre);

        }
        //Cursor cursor = manejadorBD.select("SELECT * FROM Peajes where nombre='PEAJE SIBERIA'");
        Cursor cursor = manejadorBD.select("SELECT * FROM Peaje where nombre LIKE '"+ datoNombre+"' ORDER BY id ASC");
        while (cursor.moveToNext())

            ManejadorDeDatos.getInstance().Setearinformacion(
                    cursor.getString(cursor.getColumnIndex("informacionadicional")),
                    cursor.getString(cursor.getColumnIndex("valorcategoriai")),
                    cursor.getString(cursor.getColumnIndex("valorcategoriaii")),
                    cursor.getString(cursor.getColumnIndex("valorcategoriaiii")),
                    cursor.getString(cursor.getColumnIndex("valorcategoriaiv")),
                    cursor.getString(cursor.getColumnIndex("valorcategoriav")),
                    cursor.getString(cursor.getColumnIndex("valorcategoriavi")),
                    cursor.getString(cursor.getColumnIndex("valorcategoriavii")),
                    cursor.getString(cursor.getColumnIndex("valorcategoriaviii")));

        cursor.close();

        Cursor cursor1 = manejadorBD.select("SELECT * FROM Peaje where nombre LIKE '"+ datoNombre+"' ORDER BY id ASC");
        while (cursor1.moveToNext())

            ManejadorDeDatos.getInstance().SetearCoordenadas(
                    cursor1.getFloat(cursor1.getColumnIndex("latitud")),
                    cursor1.getFloat(cursor.getColumnIndex("longitud")));

        cursor.close();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

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
        ManejadorDeDatos.ObtenerListaValorCategoria().clear();
        //ManejadorDeDatos.ObtenerListaInformacion().clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.informacion_peajes, menu);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void LlenarLista(View v) {

        final ArrayAdapter<String> infoAdapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1, ManejadorDeDatos.ObtenerListaInformacion());

        ((ListView)findViewById(R.id.lv_info)).setAdapter(infoAdapter);


        final ArrayAdapter<String> costosAdapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1, ManejadorDeDatos.ObtenerListaValorCategoria());
        ((ListView)findViewById(R.id.lv_costos)).setAdapter(costosAdapter);


    }

    public void OnClickBotonUbicacion(View v) {
        Intent intencion = new Intent(getApplicationContext(), MapsActivity.class);
        String datoNombre = nombrePeaje.getText().toString();
        intencion.putExtra("DATO", datoNombre);
        startActivity(intencion);
    }
    //ensayo.setText(ManejadorDeDatos.ObtenerListaLatitud().get(0).toString());
    //ensayo2.setText(ManejadorDeDatos.ObtenerListaLongitud().get(0).toString());

    /*public static String Ubicacion(){
        ArrayList<String> datos = ManejadorDeDatos.ObtenerListaLatitud();
        return datos.get(0);
    }*/

}