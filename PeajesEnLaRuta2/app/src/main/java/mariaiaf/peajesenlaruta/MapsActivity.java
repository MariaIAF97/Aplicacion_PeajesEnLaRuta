package mariaiaf.peajesenlaruta;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static Activity activity;
    ManejadorBaseDeDatos manejadorBD;
    String datoNombre;
    //InformacionPeajes nombrePeaje = new InformacionPeajes();
    //String nombreP= nombrePeaje.datoNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intencion=getIntent();
        Bundle extras=intencion.getExtras();

        if(extras!=null)
        {
            datoNombre=extras.getString("DATO");
        }


        //Cursor cursor = manejadorBD.select("SELECT * FROM Peajes where nombre='PEAJE SIBERIA'");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        //ManejadorDeDatos.ObtenerListaLatitud().get(0);
        // Add a marker in Sydney and move the camera
        LatLng peajeSeleccionado = new LatLng(ManejadorDeDatos.ObtenerListaLatitud().get(0), ManejadorDeDatos.ObtenerListaLongitud().get(0));
        //LatLng medellin2 = new LatLng(6.2, -75);
        //LatLng medellin3 = new LatLng(6.2, -75.5);
        //mMap.addMarker(new MarkerOptions().position(medellin).title("Marker in Medellin"));
        mMap.addMarker(new MarkerOptions().position(peajeSeleccionado).title(datoNombre));
        // mMap.addMarker(new MarkerOptions().position(medellin2).title("Marker in Medellin"));
        //mMap.addMarker(new MarkerOptions().position(medellin3).title("Marker in Medellin"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(peajeSeleccionado,50));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(medellin2,6));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(medellin3,6));
    }
}

