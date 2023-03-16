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


        val holidayType = findViewById<TextView>(R.id.holidayType)
        val txtType = intent.getStringExtra("type")
        holidayType.setText(txtType)

        var monthNameShort : String = ""





        val nameTextView = findViewById<TextView>(R.id.nameTextView)

        val txtName = intent.getStringExtra("name")

        nameTextView.setText(txtName)


        val txtdescription = findViewById<TextView>(R.id.txtdescription)

        val description = intent.getStringExtra("description")

        txtdescription.setText(description)

        val txtDate = findViewById<TextView>(R.id.txtDate)

        val day = intent.getStringExtra("day")

        txtDate.setText(day)


        val txtMonth = findViewById<TextView>(R.id.txtMonth)

        val month = intent.getStringExtra("month")



        when (month) {
            "1" -> monthNameShort = "jan"
            "2" -> monthNameShort=("feb")
            "3" -> monthNameShort=("mar")
            "4" -> monthNameShort=("apr")
            "5" -> monthNameShort=("May")
            "6" -> monthNameShort=("June")
            "7" -> monthNameShort=("July")
            "8" -> monthNameShort=("August")
            "9" -> monthNameShort=("September")
            "10" -> monthNameShort=("October")
            "11" -> monthNameShort=("November")
            "12" -> monthNameShort=("December")
            else -> monthNameShort=("Invalid month number")
        }

        txtMonth.setText(monthNameShort)


    }
}
