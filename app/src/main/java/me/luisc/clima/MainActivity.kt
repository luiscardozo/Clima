package me.luisc.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getForecastButton = findViewById<Button>(R.id.getForecastButton)
        getForecastButton.setOnClickListener {
            val weatherDaysIntent = Intent(applicationContext, WeatherDaysActivity::class.java)

            val searchEditText = findViewById<EditText>(R.id.searchEditText)
            weatherDaysIntent.putExtra("cityToSearch", searchEditText.text.toString())

            startActivity(weatherDaysIntent)
        }
    }
}
