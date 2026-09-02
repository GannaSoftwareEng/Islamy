package com.example.islamy

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.batoulapps.adhan2.CalculationMethod
import com.batoulapps.adhan2.Coordinates
import com.batoulapps.adhan2.PrayerTimes
import com.batoulapps.adhan2.data.DateComponents
import com.superinterns.islamy.R
import java.util.Date
import kotlin.time.Instant
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone
import kotlin.time.ExperimentalTime

class PrayerActivity : AppCompatActivity() {

    lateinit var coordinatesLocation: Coordinates

    private lateinit var locationHelper: LocationHelper
    private val fallbackCoordinates = Coordinates(30.0444, 31.2357) // Default: Cairo

    @OptIn(ExperimentalTime::class)
    private fun Instant.asDate() = Date(toEpochMilliseconds())


    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prayer)

        val today = LocalDate.now()
        val date = DateComponents(today.year, today.monthValue, today.dayOfMonth)
        val fajrTime = findViewById<TextView>(R.id.fajrTime)
        val dhuhrTime = findViewById<TextView>(R.id.dhuhrTime)
        val asrTime = findViewById<TextView>(R.id.asrTime)
        val maghribTime = findViewById<TextView>(R.id.maghribTime)
        val ishaTime = findViewById<TextView>(R.id.ishaTime)
        val dateText = findViewById<TextView>(R.id.dateText)
        val hijrahDate = HijrahDate.now()



        locationHelper = LocationHelper(this)
        dateText.text =today.dayOfWeek.toString().substring(0, 3).toString() + ' ' + today.dayOfMonth +' ' + today.month.toString().substring(0, 3);



// 2. Format as readable text (e.g., "15 Ramadan 1447 AH" or in Arabic "15 رمضان 1447")
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ar")) // Use Locale.ENGLISH for English
        val formattedHijriDate = hijrahDate.format(formatter)

        if (locationHelper.hasLocationPermission()) {
            locationHelper.getCurrentCoordinates { coordinates ->
                val finalCoordinates = coordinates ?: fallbackCoordinates
                coordinatesLocation =
                    Coordinates(finalCoordinates.latitude, finalCoordinates.longitude)

                val params = CalculationMethod.EGYPTIAN.parameters

                val prayerTimes = PrayerTimes(coordinatesLocation, date, params)

                val formatter = SimpleDateFormat("hh:mm a")
                formatter.timeZone = TimeZone.getTimeZone("Africa/Cairo")

                Log.w("locationlog", "Latitude : " + finalCoordinates.latitude + "Longitude : " + finalCoordinates.longitude)
                fajrTime.text = formatter.format(prayerTimes.fajr.asDate())
                dhuhrTime.text = formatter.format(prayerTimes.dhuhr.asDate())
                asrTime.text = formatter.format(prayerTimes.asr.asDate())
                maghribTime.text = formatter.format(prayerTimes.maghrib.asDate())
                ishaTime.text = formatter.format(prayerTimes.isha.asDate())
            } }else {

                Log.w("locationlog", "Location permission not granted. Requesting permissions...")
                // Request permissions if not granted
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    1001
                )
            }
        }


    }



