package com.example.weatherforecastapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecastapp.databinding.CardViewDesignBinding
import com.example.weatherforecastapp.data.model.Forecastday

class WeatherForecastAdapter(
    private val largeNewsList: List<Forecastday>?
) : RecyclerView.Adapter<WeatherForecastAdapter.WeatherForecastViewHolder>() {

    private lateinit var binding: CardViewDesignBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        binding = CardViewDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        val largeNews = largeNewsList?.get(position)
        if (largeNews != null) {
            holder.bind(largeNews)
        }
    }

    override fun getItemCount(): Int = largeNewsList?.size!!

    class WeatherForecastViewHolder(
        private val binding: CardViewDesignBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(singleDayforeCast: Forecastday) {
            binding.tvDate.text = "Date : ${singleDayforeCast.date}"
            binding.tvMax.text = "Max Temp : ${singleDayforeCast.day?.maxtempC.toString()}"
            binding.tvMin.text = "Min Temp : ${singleDayforeCast.day?.mintempC.toString()}"
            binding.tvHumid.text = "Humidity : ${singleDayforeCast.day?.avghumidity.toString()}"

            singleDayforeCast.day?.condition?.icon.let { url ->
                Glide.with(binding.imageview.context)
                    .load("https:$url")
                    .into(binding.imageview)

                return
            }
        }
    }
}