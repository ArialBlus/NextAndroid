package com.example.basedatal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.basedatal.R
import com.example.basedatal.entities.City

class CityAdapter(private val context: Context, private var cityList: List<City>,
    private val onEditClick: (City)-> Unit,
    private val onDeleteClick: (City) ->Unit): BaseAdapter(){
    override fun getCount(): Int {
        return cityList.size
    }

    override fun getItem(position: Int): Any {
        return cityList[position]
    }

    override fun getItemId(position: Int): Long {
        return cityList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_ciudad, parent, false)
        val city = cityList[position]

        view.findViewById<TextView>(R.id.tvNameCity).text = city.name
        view.findViewById<TextView>(R.id.tvDescriptionCity).text = city.description
        view.findViewById<TextView>(R.id.tvPopulationCity).text = city.population.toString()

        val btnEdit= view.findViewById<ImageButton>(R.id.btnEdit)
        val btnDelete = view.findViewById<ImageButton>(R.id.btnDelete)
        //binding.lvRegistros.adapter=cityListAdapter

        btnEdit.setOnClickListener {
            onEditClick(city)
        }

        //lo mismo va a eliminar
        btnDelete.setOnClickListener {
            onDeleteClick(city)
        }
        return view
    }

    fun updateCities(newCityList: List<City>){
        cityList = newCityList
        notifyDataSetChanged()
    }



}