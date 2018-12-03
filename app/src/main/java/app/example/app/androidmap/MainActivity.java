package app.example.app.androidmap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap _map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment map_fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        map_fragment.getMapAsync(MainActivity.this);
    }


    @Override
    public void onMapReady(GoogleMap map) {
        _map = map;

        //Sets default location to the map
        LatLng chennai = new LatLng(13.0827, 80.2707);


        //Radius circle for given LatLng
        /*CircleOptions circle = new CircleOptions();
        circle.radius(10000);
        circle.center(chennai);
        circle.fillColor(Color.parseColor("#E9E9E9"));
        circle.strokeColor(Color.GREEN);
        circle.strokeWidth(4);*/


        //To add marker and set default focus area of the map
        /*_map.addMarker(new MarkerOptions().position(chennai).title("Chennai"));
        _map.moveCamera(CameraUpdateFactory.newLatLng(chennai));*/


        //Customized Map Marker
        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(13.0827, 80.2707));
        marker.title("Chennai");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.auto));
        marker.draggable(false);
        marker.rotation(30);
        _map.addMarker(marker);
        _map.moveCamera(CameraUpdateFactory.newLatLng(chennai));


        //To set Min and Max Zoom level of map
        _map.setMinZoomPreference(12);
        _map.setMaxZoomPreference(15);


        //TO change the Map Type
        _map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        //To add the radius circle to the map
        /*_map.addCircle(circle);*/


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (result == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else {
                _map.setMyLocationEnabled(true);
            }
        }


        _map.getUiSettings().setZoomControlsEnabled(true);
        _map.getUiSettings().setMyLocationButtonEnabled(true);
        _map.getUiSettings().setCompassEnabled(true);

    }
}
