package me.luisc.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Response

class WeatherDaysActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_days)

        val lvWeatherDays = findViewById<ListView>(R.id.forecastListView)

        val randomThings = listOf("Hola", "Mundo", "Kotlin","Hola", "Mundo", "Kotlin", "Hola", "Mundo", "Kotlin","Hola", "Mundo", "Kotlin", "Hola", "Mundo", "Kotlin","Hola", "Mundo", "Kotlin", "Hola", "Mundo", "Kotlin","Hola", "Mundo", "Kotlin", "Hola", "Mundo", "Kotlin","Hola", "Mundo", "Kotlin", "Hola", "Mundo", "Kotlin","Hola", "Mundo", "Kotlin")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, randomThings)

        lvWeatherDays.adapter = adapter

        val retriever = WeatherRetriever()

        val callback = object : retrofit2.Callback<List<Forecast>>{

            override fun onResponse(call: Call<List<Forecast>>, response: Response<List<Forecast>>) {
                println("Obtuvimos una respuesta")
                println(response.body())
                for(forecastDay in response.body()!!){
                    println("High: ${forecastDay.high}, Low: ${forecastDay.low}")
                }
            }

            override fun onFailure(call: Call<List<Forecast>>, t: Throwable) {
                println("Obtuvimos un error")
            }
        }

        retriever.getForecast(callback)
    }
}
