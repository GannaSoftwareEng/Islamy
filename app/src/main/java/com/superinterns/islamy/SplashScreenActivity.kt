package com.superinterns.islamy;

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class SplashScreenActivity : AppCompatActivity() {


    companion object {
        private const val SPLASH_DURATION = 1800L // ms before navigating
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.azkarhome)


        val icon = findViewById<android.widget.TextView>(R.id.splashIcon)
        val title = findViewById<android.widget.TextView>(R.id.splashTitle)

        icon.scaleX = 0.6f
        icon.scaleY = 0.6f

        val iconFade = ObjectAnimator.ofFloat(icon, "alpha", 0f, 1f)
        val iconScaleX = ObjectAnimator.ofFloat(icon, "scaleX", 0.6f, 1f)
        val iconScaleY = ObjectAnimator.ofFloat(icon, "scaleY", 0.6f, 1f)

        val titleFade = ObjectAnimator.ofFloat(title, "alpha", 0f, 1f)
        val titleTranslate = ObjectAnimator.ofFloat(title, "translationY", 30f, 0f)

        val iconSet = AnimatorSet().apply {
            playTogether(iconFade, iconScaleX, iconScaleY)
            duration = 700
            interpolator = DecelerateInterpolator()
        }

        val titleSet = AnimatorSet().apply {
            playTogether(titleFade, titleTranslate)
            duration = 600
            startDelay = 300
            interpolator = DecelerateInterpolator()
        }

        AnimatorSet().apply {
            playTogether(iconSet, titleSet)
            start()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomePage::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, SPLASH_DURATION)
    }




}