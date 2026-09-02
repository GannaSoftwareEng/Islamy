package com.superinterns.islamy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.islamy.PrayerActivity;


public class HomePage extends AppCompatActivity {

    LinearLayout lnQibla ;
    LinearLayout androidblock;
    LinearLayout salahBlockln;



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




    }


}