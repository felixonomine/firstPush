package com.example.a8340

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForcastRepository {

    private val _weeklyForcast = MutableLiveData<List<DailyForcast>>()
    val weeklyForcast:LiveData<List<DailyForcast>> = _weeklyForcast

    fun loadForcast(password:String){
        val randomValues= List(10){ Random.nextFloat().rem(100)*100}
        val forcastItems=randomValues.map { temp->
            DailyForcast(temp,getTempDescription(temp))
        }
        _weeklyForcast.setValue(forcastItems)
    }

    private fun getTempDescription(temp:Float) : String{
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f)->"Anything below 0 does not make sence"
            in 0f.rangeTo(32f)-> "Way too cold"
            in 32f.rangeTo(55f)-> "Colder than i would prefer"
            in 556f.rangeTo(65f)-> "Getting better"
            in 65f.rangeTo(80f)-> "That is the sweet spot!"
            in 80f.rangeTo(90f)-> "Getting a little warm"
            in 90f.rangeTo(100f)-> "Where is the A/C?"
            in 100f.rangeTo(Float.MAX_VALUE)-> "What is this,Arizona?"
            else -> "Does not compute"
        }

    }
}