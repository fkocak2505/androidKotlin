package tr.com.cs.googlemaplab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity  {


    MapFragment mapFragment;
    CameraPosition cameraPosition;
    Button button;

    double x = 39.925533;
    double y = 32.866287;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 39.14583;
                y = 34.16389;
                onResume();
            }
        });

        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(onMapReadyCallback1(x,y));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();

    }

    public OnMapReadyCallback onMapReadyCallback1(final Double x, final Double y){
        return new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                MarkerOptions marker = new MarkerOptions().position(new LatLng(x, y)).title("Hello Maps ");
                googleMap.addMarker(marker);

                // Showing / hiding your current location
                googleMap.setMyLocationEnabled(true);
                // Enable / Disable zooming controls
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                // Enable / Disable my location button
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                // Enable / Disable Compass icon
                googleMap.getUiSettings().setCompassEnabled(true);
                // Enable / Disable Rotate gesture
                googleMap.getUiSettings().setRotateGesturesEnabled(true);
                // Enable / Disable zooming functionality
                googleMap.getUiSettings().setZoomGesturesEnabled(true);

                cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(x,
                                y)).zoom(7).build();

                googleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
            }
        };
    }
}
