package com.example.basedatal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.basedatal.dao.DatabaseBuilder
import com.example.basedatal.databinding.FragmentCiudadesBinding
import com.example.basedatal.entities.City
import kotlinx.coroutines.launch


class Ciudades : Fragment() {
    private lateinit var binding: FragmentCiudadesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCiudadesBinding.inflate(layoutInflater)
        startCity()
        return binding.root
    }

    private fun startCity() {
        binding.btnGuardar.setOnClickListener{
            saveCity()
        }
    }

    private fun saveCity() {
        val context= requireContext()
        val db=DatabaseBuilder.getInstance((context))
        val cityDao= db.cityDao()
        val name= binding.tfName.editText?.text.toString()
        val description= binding.tfName2.editText?.text.toString()
        val population= binding.tfName3.editText?.text.toString().toInt()

        val city=City(0, name, description, population)
        viewLifecycleOwner.lifecycleScope.launch {
            cityDao.insert(city)

        }
        binding.tfName.editText?.setText("")
        binding.tfName2.editText?.setText("")
        binding.tfName3.editText?.setText("")
        binding.tfName.requestFocus()
        findNavController().popBackStack()


    }


}