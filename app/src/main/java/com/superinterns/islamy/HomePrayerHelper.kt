package com.superinterns.islamy

import android.os.Build
import androidx.annotation.RequiresApi
import com.batoulapps.adhan2.CalculationMethod
import com.batoulapps.adhan2.Coordinates
import com.batoulapps.adhan2.PrayerTimes
import com.batoulapps.adhan2.data.DateComponents
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class UpcomingPrayer(val name: String, val timeText: String)

object HomePrayerHelper {

    @OptIn(ExperimentalTime::class)
    private fun Instant.asDate() = Date(toEpochMilliseconds())

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalTime::class)
    @JvmStatic
    fun getUpcomingPrayer(coordinates: Coordinates): UpcomingPrayer {
        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

        val params = CalculationMethod.EGYPTIAN.parameters
        val today = LocalDate.now()
        val todayComponents = DateComponents(today.year, today.monthValue, today.dayOfMonth)
        val prayerTimes = PrayerTimes(coordinates, todayComponents, params)
        val now = System.currentTimeMillis()

        val prayers = linkedMapOf(
            "Fajr" to prayerTimes.fajr,
            "Dhuhr" to prayerTimes.dhuhr,
            "Asr" to prayerTimes.asr,
            "Maghrib" to prayerTimes.maghrib,
            "Isha" to prayerTimes.isha
        )

        for ((name, instant) in prayers) {
            if (instant.toEpochMilliseconds() > now) {
                return UpcomingPrayer(name, formatter.format(instant.asDate()))
            }
        }

        // All of today's prayers have passed, so the next one is tomorrow's Fajr
        val tomorrow = today.plusDays(1)
        val tomorrowComponents = DateComponents(tomorrow.year, tomorrow.monthValue, tomorrow.dayOfMonth)
        val tomorrowTimes = PrayerTimes(coordinates, tomorrowComponents, params)
        return UpcomingPrayer("Fajr", formatter.format(tomorrowTimes.fajr.asDate()))




        
    }
}
