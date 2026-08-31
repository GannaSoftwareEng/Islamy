package com.superinterns.islamy;

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AzkarHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.azkarhome)


        val btnAzkar = findViewById<LinearLayout>(R.id.btnAzkar)

        btnAzkar.setOnClickListener {
            val intent = Intent(this, AzkarActivity::class.java)
            startActivity(intent)
        }
    }
}