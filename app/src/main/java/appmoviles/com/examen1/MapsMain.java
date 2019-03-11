package appmoviles.com.examen1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsMain extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMapClickListener, View.OnClickListener {

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private GoogleMap mMap;

    // Biblioteca
    private static final LatLng RECT_1_1 = new LatLng(3.341923, -76.530067);
    private static final LatLng RECT_1_2 = new LatLng(3.341923, -76.529794);
    private static final LatLng RECT_1_3 = new LatLng(3.341666, -76.530078);
    private static final LatLng RECT_1_4 = new LatLng(3.341655, -76.529788);

    // Central
    private static final LatLng RECT_2_1 = new LatLng(3.342250, -76.529725);
    private static final LatLng RECT_2_2 = new LatLng(3.342260, -76.529494);
    private static final LatLng RECT_2_3 = new LatLng(3.341907, -76.529708);
    private static final LatLng RECT_2_4 = new LatLng(3.341928, -76.529521);

    // Edificio C
    private static final LatLng RECT_3_1 = new LatLng(3.341238, -76.530422);
    private static final LatLng RECT_3_2 = new LatLng(3.341227, -76.529859);
    private static final LatLng RECT_3_3 = new LatLng(3.341072, -76.530422);
    private static final LatLng RECT_3_4 = new LatLng(3.341061, -76.529859);


    // Edificio M
    private static final LatLng RECT_4_1 = new LatLng(3.342641,-76.530508);
    private static final LatLng RECT_4_2 = new LatLng(3.342700,-76.530063);
    private static final LatLng RECT_4_3 = new LatLng(3.342394,-76.530486);
    private static final LatLng RECT_4_4 = new LatLng(3.342416,-76.530079);

    private static final float DEFAULT_ZOOM = 18;
    private Boolean mLocationPermissionsGranted = false;
    private LocationManager manager;
    private MarkerOptions concurrentMarker;
    private boolean first;
    private boolean visible;
    private Button boton_acciones;
    boolean encontro_edificio = false;
    boolean dificultad = false;
    boolean encontro_biblioteca = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_main);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        getLocationPermission();
        visible = false;
        metodos();
    }

    private void metodos() {
        boton_acciones = findViewById(R.id.boton_accion);
        boton_acciones.setOnClickListener(this);
    }

    private void contruirRectangulos() {
        biblioteca();
        central();
        edificioC();
        edificioM();
    }

    private void edificioC() {
        Polyline line1 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_3_1, RECT_3_2)
                .width(5)
                .color(Color.BLUE));
        Polyline line2 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_3_1, RECT_3_3)
                .width(5)
                .color(Color.BLUE));
        Polyline line3 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_3_4, RECT_3_2)
                .width(5)
                .color(Color.BLUE));
        Polyline line4 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_3_4, RECT_3_3)
                .width(5)
                .color(Color.BLUE));
    }

    private void central() {
        Polyline line1 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_2_1, RECT_2_2)
                .width(5)
                .color(Color.GREEN));
        Polyline line2 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_2_3, RECT_2_1)
                .width(5)
                .color(Color.GREEN));
        Polyline line3 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_2_4, RECT_2_2)
                .width(5)
                .color(Color.GREEN));
        Polyline line4 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_2_4, RECT_2_3)
                .width(5)
                .color(Color.GREEN));
    }

    private void biblioteca() {
        Polyline line1 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_1_1, RECT_1_2)
                .width(5)
                .color(Color.RED));
        Polyline line2 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_1_1, RECT_1_3)
                .width(5)
                .color(Color.RED));
        Polyline line3 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_1_4, RECT_1_2)
                .width(5)
                .color(Color.RED));
        Polyline line4 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_1_4, RECT_1_3)
                .width(5)
                .color(Color.RED));
    }

    private void edificioM() {
        Polyline line1 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_4_1, RECT_4_2)
                .width(5)
                .color(Color.MAGENTA));
        Polyline line2 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_4_1, RECT_4_3)
                .width(5)
                .color(Color.MAGENTA));
        Polyline line3 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_4_2, RECT_4_4)
                .width(5)
                .color(Color.MAGENTA));
        Polyline line4 = mMap.addPolyline(new PolylineOptions()
                .add(RECT_4_4, RECT_4_3)
                .width(5)
                .color(Color.MAGENTA));
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
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(this);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setOnMarkerClickListener(this);

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng posicion = new LatLng(location.getLatitude(), location.getLongitude());
                if (first == false) {
                    concurrentMarker = new MarkerOptions()
                            .icon(BitmapDescriptorFactory
                                    .fromResource(R.mipmap.ic_person))
                            .position(posicion)
                            .title("I");
                    mMap.addMarker(concurrentMarker);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, DEFAULT_ZOOM));
                    first = true;
                } else {
                    concurrentMarker.position(posicion);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(posicion));
                    encontrarRectangulo(posicion);
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        });

        contruirRectangulos();

    }

    private void encontrarRectangulo(LatLng posicion) {
        encontrarBiblioteca(posicion);
        encontrarCentral(posicion);
        encontrarEdificioC(posicion);
        encontrarEdificioM(posicion);
    }

    private void encontrarEdificioM(LatLng posicion) {
        if(posicion.latitude <= RECT_4_1.latitude
                && posicion.latitude >= RECT_4_4.latitude
                && posicion.longitude >= RECT_4_1.longitude
                && posicion.longitude <= RECT_4_4.longitude){
            encontro_edificio = true;
            dificultad = true;
        }
        if(encontro_edificio){
            boton_acciones.setVisibility(View.VISIBLE);
            boton_acciones.setText("Preguntas");
        }
        else{
            boton_acciones.setVisibility(View.GONE);
        }
    }

    private void encontrarEdificioC(LatLng posicion) {
        if(posicion.latitude <= RECT_3_1.latitude
                && posicion.latitude >= RECT_3_4.latitude
                && posicion.longitude >= RECT_3_1.longitude
                && posicion.longitude <= RECT_3_4.longitude){
            encontro_edificio = true;
            dificultad = true;
        }
        if(encontro_edificio){
           boton_acciones.setVisibility(View.VISIBLE);
           boton_acciones.setText("Preguntas");
        }
        else{
            boton_acciones.setVisibility(View.GONE);
        }
    }

    private void encontrarCentral(LatLng posicion) {
        encontro_edificio = false;
        if(posicion.latitude <= RECT_2_1.latitude
                && posicion.latitude >= RECT_2_4.latitude
                && posicion.longitude >= RECT_2_1.longitude
                && posicion.longitude <= RECT_2_4.longitude){
            encontro_edificio = true;
            dificultad = false;
        }
        if(encontro_edificio){
            boton_acciones.setVisibility(View.VISIBLE);
            boton_acciones.setText("Preguntas");
        }
        else{
            boton_acciones.setVisibility(View.GONE);
        }
    }

    private void encontrarBiblioteca(LatLng posicion) {
        encontro_biblioteca = false;
        if(posicion.latitude <= RECT_1_1.latitude
                && posicion.latitude >= RECT_1_4.latitude
                && posicion.longitude >= RECT_1_1.longitude
                && posicion.longitude <= RECT_1_4.longitude){
            encontro_biblioteca = true;
        }
        if(encontro_biblioteca){
            boton_acciones.setVisibility(View.VISIBLE);
            boton_acciones.setText("Canjear");
        }
        else{
            boton_acciones.setVisibility(View.GONE);
        }
    }

    /**
     *
     */
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                        Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                            Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
                        initMap();
                    } else {
                        ActivityCompat.requestPermissions(this,
                                permissions,
                                LOCATION_PERMISSION_REQUEST_CODE);
                    }
                } else {
                    ActivityCompat.requestPermissions(this,
                            permissions,
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    /**
     *
     */
    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        LatLng posicion = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, DEFAULT_ZOOM));

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onClick(View view) {
        if(encontro_edificio){
            Intent i = new Intent(MapsMain.this, ActividadPreguntas.class);
            if(dificultad){
                i.putExtra("dificultad","100");
            }else{
                i.putExtra("dificultad","1");
            }
            startActivity(i);
        }
        if(encontro_biblioteca){
            Intent i = new Intent(MapsMain.this, ActividadCanje.class);
            startActivity(i);
        }
    }
}



