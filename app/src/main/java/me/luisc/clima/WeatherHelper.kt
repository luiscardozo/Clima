package me.luisc.clima

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherAPI {
    @GET("bins/lrxvl")
    fun getForecast() : Call<List<Forecast>>
}

class Forecast (val high: String, val low: String)

class WeatherRetriever {
    private val service : WeatherAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.myjson.com/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(WeatherAPI::class.java)
    }

    fun getForecast(callback: Callback<List<Forecast>>){
        val call = service.getForecast()
        call.enqueue(callback)
    }
}