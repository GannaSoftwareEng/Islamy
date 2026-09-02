package com.superinterns.islamy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.batoulapps.adhan2.Coordinates;
import com.example.islamy.LocationHelper;
import com.example.islamy.PrayerActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomePage extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 200;

    LinearLayout lnQibla ;
    LinearLayout androidblock;
    LinearLayout salahBlockln;

    TextView prayerNameText;
    TextView prayerTimeText;
    TextView locationText;

    private LocationHelper locationHelper;
    private final Coordinates fallbackCoordinates = new Coordinates(30.0444, 31.2357); // Default: Cairo



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        lnQibla = findViewById(R.id.lnQibla);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lnQibla.setOnClickListener(v -> {
            startActivity(new Intent(this,QiblaActivity.class));
        });

        androidblock = findViewById(R.id.azkarblock);
        androidblock.setOnClickListener(v -> {
            startActivity(new Intent(this,AzkarActivity.class));
        });

        salahBlockln = findViewById(R.id.salahBlock);
        salahBlockln.setOnClickListener(v -> {
            startActivity(new Intent(this, PrayerActivity.class));
        });

        prayerNameText = findViewById(R.id.prayerNameText);
        prayerTimeText = findViewById(R.id.prayerTimeText);
        locationText = findViewById(R.id.locationText);

        locationHelper = new LocationHelper(this);
        loadPrayerAndLocation();
    }

    private void loadPrayerAndLocation() {
        if (!locationHelper.hasLocationPermission()) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE
            );
            return;
        }

        locationHelper.getCurrentCoordinates(coordinates -> {
            Coordinates finalCoordinates = coordinates != null ? coordinates : fallbackCoordinates;
            updateUpcomingPrayer(finalCoordinates);
            updateLocationText(finalCoordinates);
            return kotlin.Unit.INSTANCE;
        });
    }

    private void updateUpcomingPrayer(Coordinates coordinates) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            UpcomingPrayer upcomingPrayer = HomePrayerHelper.getUpcomingPrayer(coordinates);
            prayerNameText.setText(upcomingPrayer.getName());
            prayerTimeText.setText(upcomingPrayer.getTimeText());
        }
    }

    private void updateLocationText(Coordinates coordinates) {
        new Thread(() -> {
            String placeText = null;
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(coordinates.getLatitude(), coordinates.getLongitude(), 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    String city = address.getLocality() != null ? address.getLocality() : address.getSubAdminArea();
                    String country = address.getCountryName();
                    if (city != null && country != null) {
                        placeText = city + ", " + country;
                    } else if (country != null) {
                        placeText = country;
                    }
                }
            } catch (IOException e) {
                Log.w("HomePage", "Reverse geocoding failed", e);
            }

            String finalPlaceText = placeText != null ? placeText : "Location unavailable";
            runOnUiThread(() -> locationText.setText(finalPlaceText));
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadPrayerAndLocation();
            } else {
                updateUpcomingPrayer(fallbackCoordinates);
                locationText.setText("Location unavailable");
            }
        }
    }


}
