package com.example.a8340

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DailyForcastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tempText : TextView = view.findViewById(R.id.tempText)
    private val descriptionText: TextView = view.findViewById(R.id.descriptionText)

    fun bind(dailyForcast: DailyForcast) {
        tempText.text = String.format("%.2f",dailyForcast.temp)
        descriptionText.text = dailyForcast.description
    }
}

class DailyForcastAdapter(
    private val clickHolder: (DailyForcast) ->  Unit
) : ListAdapter<DailyForcast, DailyForcastViewHolder>(DIFF_CONFIG) {


    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForcast>() {
            override fun areItemsTheSame(oldItem: DailyForcast, newItem: DailyForcast): Boolean {
                return oldItem === newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: DailyForcast, newItem: DailyForcast): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForcastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forcast,parent, false)
        return DailyForcastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DailyForcastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHolder(getItem(position))
        }
    }
}