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



        val nameTextView = findViewById<TextView>(R.id.nameTextView)

        val txtName = intent.getStringExtra("name")

        nameTextView.setText(txtName)


        val txtdescription = findViewById<TextView>(R.id.txtdescription)

        val description = intent.getStringExtra("description")

        txtdescription.setText(description)

        val txtDate = findViewById<TextView>(R.id.txtDate)

        val day = intent.getStringExtra("day")

        txtDate.setText(day)




    }
}
