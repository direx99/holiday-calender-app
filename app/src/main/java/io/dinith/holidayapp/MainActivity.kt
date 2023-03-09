package io.dinith.holidayapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import io.dinith.holidayapp.MainActivity.HolidayHolder as HolidayHolder1


class MainActivity : AppCompatActivity() {

    lateinit var holidayViewer : RecyclerView
    var holidayArray = JSONArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        holidayViewer = findViewById(R.id.Holidayviewer)
        holidayViewer.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,false)
        holidayViewer.adapter = HolidayAdapter()
        getHolidaydata()

    }

    fun getHolidaydata(){

        val url = "https://calendarific.com/api/v2/holidays?&api_key=2b7a6949248e64c1e36bebabdc862616972d9e1e&country=lk&year=2023"

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

                if (holder.txtname.text.toString().equals("Holi", ignoreCase = true)) {
                    holder.txtname.setTextColor(Color.GREEN)
                } else {
                    holder.txtname.setTextColor(Color.BLACK)
                }

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






