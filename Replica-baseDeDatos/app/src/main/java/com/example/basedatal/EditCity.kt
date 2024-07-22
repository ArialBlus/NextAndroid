package com.example.basedatal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.room.InvalidationTracker
import com.example.basedatal.adapter.CityModelFactory
import com.example.basedatal.adapter.CityViewModel
import com.example.basedatal.dao.DatabaseBuilder
import com.example.basedatal.databinding.FragmentEditCityBinding
import com.example.basedatal.entities.City


class EditCity : Fragment() {

    private lateinit var binding : FragmentEditCityBinding
    private val cityViewModel: CityViewModel by viewModels {
        CityModelFactory(DatabaseBuilder.getInstance(requireContext()).cityDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentEditCityBinding.inflate(layoutInflater)
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments= arguments ?: return
        var cityId=arguments.getInt("city_id")
        showData(cityId)

        binding.btnGuardar.setOnClickListener {
            val updateCity= City(
                id= binding.tcIdCity.text.toString().toInt(),
                name= binding.tfName.editText?.text.toString(),
                description= binding.tfName2.editText?.text.toString(),
                population= binding.tfName3.editText?.text.toString().toInt()
            )
            cityViewModel.updateCity(updateCity)
            Toast.makeText(context, "Registro Actualizado", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    private fun showData(cityId: Int) {
        cityViewModel.getCity(cityId).observe(viewLifecycleOwner, Observer{
            city-> city?.let{
                binding.tcIdCity.text=it.id.toString()

                binding.tfName.editText?.setText(it.name)
                binding.tfName2.editText?.setText(it.description)
                binding.tfName3.editText?.setText(it.population.toString())
        }
        })

    }


}