package com.example.online_crime_reporting_app.Public_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.online_crime_reporting_app.R

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor()

        setContentView(R.layout.activity_user_profile)

        setSupportActionBar(findViewById(R.id.my_toolbar3))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setStatusBarColor(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

}