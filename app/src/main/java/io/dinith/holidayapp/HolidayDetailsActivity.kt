package io.dinith.holidayapp

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import java.util.*

class HolidayDetailsActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private val handler = Handler()
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            updateTime()
            handler.postDelayed(this, 1000) // schedule the next update in 1 second
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holiday_details)


       // val holidayString = intent.getStringExtra("holiday")
        //val holiday = JSONObject(holidayString)

        textView = findViewById(R.id.Time)
        val textview = findViewById<TextView>(R.id.Time)

        // Display holiday details in the layout
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val txtName = intent.getStringExtra("txtName")
        nameTextView.setText(txtName)

        val holidayType = findViewById<TextView>(R.id.holidayType)
        val txtType = intent.getStringExtra("txtType")
        holidayType.setText(txtType)

        val bgColorResId = intent.getIntExtra("bgColorResId", R.color.purple_200)
        val roundedShape = findViewById<CardView>(R.id.roundedShape)
        roundedShape.setCardBackgroundColor(ContextCompat.getColor(applicationContext, bgColorResId))

//        val dateTextView = findViewById<TextView>(R.id.dateTextView)
//        val txtDay = intent.getStringExtra("txtDay")
//        dateTextView.text = txtDay
    }
    override fun onResume() {
        super.onResume()
        handler.post(updateTimeRunnable) // start the periodic update
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(updateTimeRunnable) // stop the periodic update
    }

    private fun updateTime() {
        val currentTime = Calendar.getInstance().time
        val sdf = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
        val formattedTime = sdf.format(currentTime)
        textView.text = formattedTime
    }
}
