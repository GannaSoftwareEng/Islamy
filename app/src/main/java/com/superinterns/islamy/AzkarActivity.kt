package com.superinterns.islamy

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class AzkarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_azkar)

        // =========================
        // Back Button
        // =========================

        val btnBack = findViewById<View>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }


        // =========================
        // Morning
        // =========================

        val cardMorning =
            findViewById<View>(R.id.cardMorning)

        cardMorning.setOnClickListener {

            val intent =
                Intent(this, AzkarDetailsActivity::class.java)

            intent.putExtra("category", "morning")

            startActivity(intent)
        }


        // =========================
        // Evening
        // =========================

        val cardEvening =
            findViewById<View>(R.id.cardEvening)

        cardEvening.setOnClickListener {

            val intent =
                Intent(this, AzkarDetailsActivity::class.java)

            intent.putExtra("category", "evening")

            startActivity(intent)
        }


        // =========================
        // Daily
        // =========================

        val cardDaily =
            findViewById<View>(R.id.cardDaily)

        cardDaily.setOnClickListener {

            val intent =
                Intent(this, AzkarDetailsActivity::class.java)

            intent.putExtra("category", "daily")

            startActivity(intent)
        }


        // =========================
        // After Prayer
        // =========================

        val cardAfterPrayer =
            findViewById<View>(R.id.cardAfterPrayer)

        cardAfterPrayer.setOnClickListener {

            val intent =
                Intent(this, AzkarDetailsActivity::class.java)

            intent.putExtra("category", "after_prayer")

            startActivity(intent)
        }


        // =========================
        // Before Sleep
        // =========================

        val cardBeforeSleep =
            findViewById<View>(R.id.cardBeforeSleep)

        cardBeforeSleep.setOnClickListener {

            val intent =
                Intent(this, AzkarDetailsActivity::class.java)

            intent.putExtra("category", "before_sleep")

            startActivity(intent)
        }


        // =========================
        // Duaa
        // =========================

        val cardDuaa =
            findViewById<View>(R.id.cardDuaa)

        cardDuaa.setOnClickListener {

            val intent =
                Intent(this, AzkarDetailsActivity::class.java)

            intent.putExtra("category", "duaa")

            startActivity(intent)
        }


        // =========================
        // Tasbeeh
        // =========================

        val cardTasbeeh =
            findViewById<View>(R.id.cardTasbeeh)

        cardTasbeeh.setOnClickListener {

            val intent =
                Intent(this, AzkarDetailsActivity::class.java)

            intent.putExtra("category", "tasbeeh")

            startActivity(intent)
        }
    }
}
