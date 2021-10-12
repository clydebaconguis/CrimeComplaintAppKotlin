package com.example.online_crime_reporting_app.Public_user

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.online_crime_reporting_app.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONStringer
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val complaintList = ArrayList<ComplaintData>()
    private lateinit var fab : ExtendedFloatingActionButton
    private lateinit var recyclerMain : RecyclerView
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor()
        setContentView(R.layout.activity_main)
        setId()
        requestQueue = Volley.newRequestQueue(this)

        networkCall()
    }
    private  fun networkCall(){

        val url = "https://script.google.com/macros/s/AKfycbxGgZol4z4gbfIbSDGnwm8CnZ6yqMqZc9lxowd37Rt1SHtviaf7AjjwGZxTcKz6kTSH/exec?action=getItem"

        val stringRequest= object : StringRequest(
            Request.Method.GET,url,
            Response.Listener{
                             parseItems(it)
            },
            Response.ErrorListener {
                Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
            }){}

        val queue = Volley.newRequestQueue(this@MainActivity)
        queue.add(stringRequest)

    }
    fun parseItems(jsonResponse: String){
        try {
            val jobj = JSONObject(jsonResponse)
            val jarray = jobj.getJSONArray("item")

            for  ( i in 0 until jarray.length()  ){

                val jo = jarray.getJSONObject(i)

                val date = jo.getString("date")
                val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
                val formattedDate = formatter.format(parser.parse(date))

                val status = jo.getString("status")
                val stat: String= if (status == "0"){
                    "For Evaluation"
                }else {
                    "Responded"
                }

                complaintList.add(ComplaintData(formattedDate,stat))
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        recyclerMain.adapter = ComplainAdapter(this, complaintList)
        recyclerMain.layoutManager = LinearLayoutManager(this)
        recyclerMain.setHasFixedSize(true)

    }
    private fun setId(){
        setSupportActionBar(findViewById(R.id.my_toolbar))
        recyclerMain = findViewById(R.id.recycler_main)
        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddReport::class.java)
            startActivity(intent)
        }
    }
    private fun setStatusBarColor(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> clickProfile()
            R.id.nav_newsAndUpdate -> Toast.makeText(this, "news selected", Toast.LENGTH_SHORT).show()
            R.id.nav_settings -> Toast.makeText(this, "settings selected", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun clickProfile(){
        val intent = Intent(this@MainActivity, UserProfile::class.java)
        startActivity(intent)
    }


}