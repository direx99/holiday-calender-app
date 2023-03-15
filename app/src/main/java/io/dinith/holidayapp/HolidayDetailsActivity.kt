package io.dinith.holidayapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONObject

class HolidayDetailsActivity : AppCompatActivity() {

    lateinit var bottom_sheet_button : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holiday_details)
        bottom_sheet_button = findViewById(R.id.bottom_sheet_button)
        supportActionBar?.hide()

        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.activity_holiday_details, null)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottom_sheet_button.setOnClickListener {
            bottomSheetDialog.show()
        }
        // val holidayString = intent.getStringExtra("holiday")
        //val holiday = JSONObject(holidayString)
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
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
