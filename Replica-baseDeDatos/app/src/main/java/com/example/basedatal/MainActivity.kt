package com.example.basedatal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Database
import com.example.basedatal.dao.DatabaseBuilder
import com.example.basedatal.databinding.ActivityMainBinding
import com.example.basedatal.entities.City
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        funMain()
    }

    private fun funMain() = runBlocking{
        val context=this@MainActivity
        val db=DatabaseBuilder.getInstance(context)
        val city1=City(0, "Masaya", "Ciudad de ls flores", 5000)

        var listCities: List<City> = listOf(city1)

        val cityDao=db.cityDao()

        listCities.forEach{
            city->cityDao.insert(city)
        }
    }
}