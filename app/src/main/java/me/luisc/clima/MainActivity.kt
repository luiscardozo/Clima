package me.luisc.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getForecastButton = findViewById<Button>(R.id.getForecastButton)
        getForecastButton.setOnClickListener {
            val weatherDaysIntent = Intent(applicationContext, WeatherDaysActivity::class.java)
            startActivity(weatherDaysIntent)
        }
    }
}
