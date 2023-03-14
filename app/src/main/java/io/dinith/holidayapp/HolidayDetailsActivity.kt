package io.dinith.holidayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject

class HolidayDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holiday_details)
        supportActionBar?.hide()


        // val holidayString = intent.getStringExtra("holiday")
        //val holiday = JSONObject(holidayString)

        // Display holiday details in the layout
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
       // nameTextView.text = holiday.getString("name")

        //val dateTextView = findViewById<TextView>(R.id.dateTextView)

        val txtType = intent.getStringExtra("txtType")
        if (txtType != null) {
            // do something with txtType value
        }

        nameTextView.setText(txtType)
        //val date = holiday.getJSONObject("date").getJSONObject("iso").getString("date")
      //  dateTextView.text = date

        // ...
    }
}
