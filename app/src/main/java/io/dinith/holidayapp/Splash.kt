package io.dinith.holidayapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {
    private val splashTimeOut:Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()


        Handler().postDelayed({
            startActivity(Intent(this, HomeView::class.java))
            finish()
        }, splashTimeOut)
    }
}
