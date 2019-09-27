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

        val retriever = WeatherRetriever()

        val callback = object : retrofit2.Callback<WeatherListFromAPI>{
            override fun onResponse(call: Call<WeatherListFromAPI>, response: Response<WeatherListFromAPI>) {
                println("Obtuvimos una respuesta")
                println("Codigo: ${response.body()?.cod}, message: ${response.body()?.message}, city = ${response.body()?.city?.name}")
                title = response.body()?.city?.name

                val listOfForecasts = mutableListOf<String>()

                var forecasts = response.body()?.list
                if (forecasts != null) {
                    for (forecast in forecasts) {
                        val fc = "Fecha: ${forecast.dt_txt}, ${forecast.weather[0].description}, temp: ${forecast.main.temp}, humedad: ${forecast.main.humidity}"
                        println(fc)
                        listOfForecasts.add(fc)
                    }
                }

                val lvWeatherDays = findViewById<ListView>(R.id.forecastListView)

                val adapter = ArrayAdapter(this@WeatherDaysActivity, android.R.layout.simple_list_item_1, listOfForecasts)

                lvWeatherDays.adapter = adapter

            }

            override fun onFailure(call: Call<WeatherListFromAPI>, t: Throwable) {
                println("Falló nuestra llamada a la API")
            }

        }

        val ciudadABuscar = intent.getStringExtra("cityToSearch") ?: ""

        println("Ciudad a Buscar: $ciudadABuscar")

        retriever.getForecast(callback, ciudadABuscar)
    }
}
