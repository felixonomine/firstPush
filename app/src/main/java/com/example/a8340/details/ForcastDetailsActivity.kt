package com.example.a8340.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a8340.R

class ForcastDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forcast_details)

        setTitle(R.string.forcast_details_)
    }
}