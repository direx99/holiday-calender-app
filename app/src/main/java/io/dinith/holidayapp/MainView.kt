package io.dinith.holidayapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.time.Year
import java.util.Locale



class MainView : AppCompatActivity() {

    lateinit var holidayViewer : RecyclerView
    var holidayArray = JSONArray()
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR).toString()
    val month = calendar.get(Calendar.MONTH) + 2 // January is 0
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val monthx = month.toString()


    val countries = listOf(
        Pair("lk", "Sri Lanka"),
        Pair("us", "United States"),
        Pair("uk", "United Kingdom"),
        Pair("in", "India"),
        Pair("jp", "Japan"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)



        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()



        // ...

        // set the visibility of progress bar to GONE initially

        val data = listOf("2000","2001","2002","2022","2023")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)


        var selectedCountry : String = ""
        var selectedYear : String = ""




        val countryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries.map { it.second })
        val defaultCountryCode = Locale.getDefault().country
        val defaultCountryIndex = countries.indexOfFirst { it.first == defaultCountryCode }











        holidayViewer = findViewById(R.id.dailyView)
        holidayViewer.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,false)
        holidayViewer.adapter = HolidayAdapter()
        getHolidaydata(year,monthx)



    }
    //
    fun getHolidaydata(year: String,month: String) {


        val url = "https://calendarific.com/api/v2/holidays?&api_key=2b7a6949248e64c1e36bebabdc862616972d9e1e&country=lk&year=$year&month=$month"

        val result = StringRequest(Request.Method.GET,url,
            Response.Listener { response ->
                try {
                    holidayArray = JSONObject(response).getJSONObject("response").getJSONArray("holidays")
                    val filteredHolidays = JSONArray()
                    for (i in 0 until holidayArray.length()) {
                        val holiday = holidayArray.getJSONObject(i)
                        val month = holiday.getJSONObject("date").getJSONObject("datetime").getString("month").toInt()
                        if (month == 1 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 2 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 3 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 4 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 5 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 6 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 7 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 8 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 9 ) {
                            filteredHolidays.put(holiday)
                        } else if (month == 10 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 11 ) {
                            filteredHolidays.put(holiday)
                        }
                        else if (month == 12 ) {
                            filteredHolidays.put(holiday)
                        }

                    }
                    holidayArray = filteredHolidays

                    holidayViewer.adapter?.notifyDataSetChanged()


                }
                catch (e : Exception){
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }

            }
            ,Response.ErrorListener{ error-> })

        Volley.newRequestQueue(applicationContext).add(result)

    }

    inner class HolidayAdapter : RecyclerView.Adapter<HolidayHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.holiday_month,parent,false)

            return HolidayHolder(view)

        }

        override fun getItemCount(): Int {
            return holidayArray.length()
        }

        @SuppressLint("ResourceAsColor")
        override fun onBindViewHolder(holder: HolidayHolder, position: Int) {
            try {
                //    holder.txtdate.text = holidayArray.getJSONObject(position).getJSONObject("date").getString("iso")
                holder.txtname.text = holidayArray.getJSONObject(position).getString("name")
                holder.txtType.text = holidayArray.getJSONObject(position).getString("primary_type")
                var getmonth = holidayArray.getJSONObject(position).getJSONObject("date").getJSONObject("datetime").getString("month")
                holder.txtDay.text  = holidayArray.getJSONObject(position).getJSONObject("date").getJSONObject("datetime").getString("day")
                //  holder.txtMonthHeader.text  = holidayArray.getJSONObject(position).getJSONObject("date").getJSONObject("datetime").getString("month")
                val month = holidayArray.getJSONObject(position).getJSONObject("date").getJSONObject("datetime").getString("month").toInt()
                if (position == 0 || month != holidayArray.getJSONObject(position - 1).getJSONObject("date").getJSONObject("datetime").getString("month").toInt()) {
                    holder.dottedBar.visibility = View.GONE
                    holder.roudedShape.visibility = View.VISIBLE


                } else {
                    holder.dottedBar.visibility = View.VISIBLE
                    holder.roudedShape.visibility = View.VISIBLE

                }

                if (holder.txtType.text.toString().equals("Public Holiday", ignoreCase = true)) {
                    holder.roudedShape.setCardBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.purple_card
                        )
                    )
                    holder.roundBorder.setCardBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.purple_round
                        )
                    )
                }

                else if (holder.txtType.text.toString().equals("Observance", ignoreCase = true)) {
                    holder.roudedShape.setCardBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.green_card
                        )
                    )
                    holder.roundBorder.setCardBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.green_round
                        )
                    )
                }

                else if (holder.txtType.text.toString().equals("Public Holiday", ignoreCase = true)) {
                    holder.roudedShape.setCardBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.purple_200
                        )
                    )
                }
                else if (holder.txtType.text.toString().equals("Public Holiday", ignoreCase = true)) {
                    holder.roudedShape.setCardBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.purple_200
                        )
                    )

                } else {
                    holder.roudedShape.setCardBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.purple_200
                        )
                    )
                    holder.roundBorder.setCardBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.purple_card
                        )
                    )
                }


                val holiday = holidayArray.getJSONObject(position)

                holder.txtname.text = holiday.getString("name")
                holder.txtType.text = holiday.getString("primary_type")







                var monthName=""
                var monthNameShort=""
                var monthNameLong=""

                when (getmonth) {
                    "1" -> monthName = "January"
                    "2" -> monthName=("February")
                    "3" -> monthName=("March")
                    "4" -> monthName=("April")
                    "5" -> monthName=("May")
                    "6" -> monthName=("June")
                    "7" -> monthName=("July")
                    "8" -> monthName=("August")
                    "9" -> monthName=("September")
                    "10" -> monthName=("October")
                    "11" -> monthName=("November")
                    "12" -> monthName=("December")
                    else -> monthName=("Invalid month number")
                }

                when (getmonth) {
                    "1" -> monthNameShort = "jan"
                    "2" -> monthNameShort=("feb")
                    "3" -> monthNameShort=("March")
                    "4" -> monthNameShort=("April")
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

                when (getmonth) {
                    "1" -> monthNameLong = "jan"
                    "2" -> monthNameLong=("feb")
                    "3" -> monthNameLong=("March")
                    "4" -> monthNameLong=("April")
                    "5" -> monthNameLong=("May")
                    "6" -> monthNameLong=("June")
                    "7" -> monthNameLong=("July")
                    "8" -> monthNameLong=("August")
                    "9" -> monthNameLong=("September")
                    "10" -> monthNameLong=("October")
                    "11" -> monthNameLong=("November")
                    "12" -> monthNameLong=("December")
                    else -> monthNameLong=("Invalid month number")
                }


                holder.txtMonth.setText( monthName)




            }catch (e:Exception){
                Toast.makeText(this@MainView, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }






    inner class HolidayHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // val txtdate : TextView =itemView.findViewById(R.id.txtdate)

        val txtname : TextView = itemView.findViewById(R.id.txtname)

        val txtType : TextView = itemView.findViewById(R.id.txtType)
        val txtDay : TextView = itemView.findViewById(R.id.txtDay)
        val txtMonth : TextView = itemView.findViewById(R.id.txtMonth)
        val roudedShape : CardView = itemView.findViewById(R.id.roudedShape)
        val roundBorder : CardView = itemView.findViewById(R.id.roundBorder)

        val dottedBar : LinearLayout = itemView.findViewById(R.id.dottedBar)





    }


}