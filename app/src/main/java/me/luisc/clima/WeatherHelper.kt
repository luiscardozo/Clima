package me.luisc.clima

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val apiKey = "#buscaTuAPIKeyEn_openweathermap.org" //sin esto no funciona

interface WeatherAPI {
    @GET("forecast?units=metric&appid=$apiKey")
    fun getForecast(@Query("q") q: String) : Call<WeatherListFromAPI>
}

class WeatherListFromAPI(val cod: String, val message: String, val list: List<WeatherList>, val city: WeatherCity)
class WeatherList(val dt: String, val dt_txt: String, val weather : List<Weather>, val main: TemperatureHumidity)
class Weather(val id: String, val main: String, val description: String, val icon: String)
class WeatherCity(val id: String, val name: String, val coord: Coordinates, val country: String)
class Coordinates(val lat: String, val lon: String)
class TemperatureHumidity(val temp: String, val temp_min: String, val temp_max: String, val humidity: String)


class WeatherRetriever {
    private val service : WeatherAPI

    init {


        val retrofit = Retrofit.Builder().baseUrl("http://api.openweathermap.org/data/2.5/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(WeatherAPI::class.java)
    }

    fun getForecast(callback: Callback<WeatherListFromAPI>, ciudadABuscar: String){

        val searchTerm = if (ciudadABuscar.isNotEmpty()) ciudadABuscar else "Ciudad del Este"

        val call = service.getForecast(searchTerm)
        call.enqueue(callback)
    }
}