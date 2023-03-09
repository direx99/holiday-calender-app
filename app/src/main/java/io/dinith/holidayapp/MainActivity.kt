package io.dinith.holidayapp

import android.graphics.Color
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.Locale


class MainActivity : AppCompatActivity() {

    lateinit var holidayViewer : RecyclerView
    var holidayArray = JSONArray()


    val countries = listOf(
        Pair("lk", "Sri Lanka"),
        Pair("us", "United States"),
        Pair("uk", "United Kingdom"),
        Pair("in", "India"),
        Pair("jp", "Japan"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val spinner1 = findViewById<Spinner>(R.id.spinner1)

        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()


        val data = listOf("2000","2001","2002","2022","2023")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)

        spinner.adapter = adapter
        spinner.setSelection(data.indexOf(currentYear))
        var selectedCountry : String = ""
        var selectedYear : String = ""


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                Toast.makeText(applicationContext,"Selected $selectedItem", Toast.LENGTH_LONG).show()
                selectedYear = selectedItem
                getHolidaydata(selectedYear, selectedCountry)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }



        val countryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries.map { it.second })
        val defaultCountryCode = Locale.getDefault().country
        val defaultCountryIndex = countries.indexOfFirst { it.first == defaultCountryCode }

        spinner1.adapter = countryAdapter
        spinner1.setSelection(defaultCountryIndex)



        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                Toast.makeText(applicationContext,"Selected $selectedItem", Toast.LENGTH_LONG).show()

                // Retrieve the corresponding country code from the list of countries
                selectedCountry = countries[position].first

                getHolidaydata(selectedYear, selectedCountry)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        holidayViewer = findViewById(R.id.Holidayviewer)
        holidayViewer.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,false)
        holidayViewer.adapter = HolidayAdapter()




    }
//
    fun getHolidaydata(selectedYear : String , selectedCountry : String) {

        val url = "https://calendarific.com/api/v2/holidays?&api_key=6b1d83ebf975d4d885e9635b347fb5d51e963af4&country=$selectedCountry&year=$selectedYear"

        val result = StringRequest(Request.Method.GET,url,
            Response.Listener { response ->
                try {
                    holidayArray = JSONObject(response).getJSONObject("response").getJSONArray("holidays")
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

            val view = LayoutInflater.from(parent.context).inflate(R.layout.holiday,parent,false)
            return HolidayHolder(view)

        }

        override fun getItemCount(): Int {
            return holidayArray.length()
        }

        override fun onBindViewHolder(holder: HolidayHolder, position: Int) {
            try {
                holder.txtdate.text = holidayArray.getJSONObject(position).getJSONObject("date").getString("iso")
                holder.txtname.text = holidayArray.getJSONObject(position).getString("name")

//                if (holder.txtname.text.toString().equals("Holi", ignoreCase = true)) {
//                    holder.txtname.setTextColor(Color.GREEN)
//                } else {
//                    holder.txtname.setTextColor(Color.BLACK)
//                }

            }catch (e:Exception){
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }



    inner class HolidayHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtdate : TextView =itemView.findViewById(R.id.txtdate)
        val txtname : TextView = itemView.findViewById(R.id.txtname)
    }


}






