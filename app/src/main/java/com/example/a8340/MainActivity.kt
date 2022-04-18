package com.example.a8340

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a8340.details.ForcastDetailsActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private val forcastRepository = ForcastRepository()

    // region Setup methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Password: EditText = findViewById(R.id.Password)
        val title: TextView = findViewById(R.id.title)
        val button:Button = findViewById(R.id.button)
        val icon:ImageView = findViewById(R.id.icon)

        button.setOnClickListener {
            val summit: String = Password.text.toString()

            if( summit.length !=5) {
             Toast.makeText(this,"please enter valid password",Toast.LENGTH_SHORT).show()
            }else{
                forcastRepository.loadForcast(summit)
            }
        }

        val forcastList:RecyclerView = findViewById(R.id.forcastList)
        forcastList.layoutManager = LinearLayoutManager(this)
        val dailyForcastAdapter = DailyForcastAdapter() {forcastItem->
           val forcastDetailsIntent = Intent(this,ForcastDetailsActivity::class.java)
            startActivity(forcastDetailsIntent)
        }
        forcastList.adapter = dailyForcastAdapter

        val weeklyForcastObserver = Observer<List<DailyForcast>>{forccastItem->
            // update our list adaptor
            dailyForcastAdapter.submitList(forccastItem)

        }
     forcastRepository.weeklyForcast.observe(this, weeklyForcastObserver)
    }
    }

