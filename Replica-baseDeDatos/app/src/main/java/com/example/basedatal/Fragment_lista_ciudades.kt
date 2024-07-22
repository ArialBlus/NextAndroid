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
import com.example.basedatal.adapter.CityAdapter
import com.example.basedatal.adapter.CityModelFactory
import com.example.basedatal.adapter.CityViewModel
import com.example.basedatal.dao.DatabaseBuilder
import com.example.basedatal.databinding.FragmentListaCiudadesBinding
import com.example.basedatal.entities.City


class Fragment_lista_ciudades : Fragment() {
    private lateinit var binding: FragmentListaCiudadesBinding

    private val cityViewModel : CityViewModel by viewModels {
        CityModelFactory(DatabaseBuilder.getInstance(requireContext()).cityDao())
    }

    private lateinit var cityListAdapter: CityAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentListaCiudadesBinding.inflate(layoutInflater)
        startList()
        return binding.root
    }

    private fun startList() {
        binding.BtnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_ciudades_to_lista_ciudades)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityListAdapter= CityAdapter(requireContext(), emptyList(), this::onEditClick, this::onDeleteClick)
        binding.lvRegistro.adapter=cityListAdapter

        cityViewModel.allCities.observe(viewLifecycleOwner, Observer{
            cities -> cityListAdapter.updateCities(cities)
        })
    }
    private fun onEditClick(city: City){
        val bundle= Bundle()
        bundle.putInt("city_id", city.id)
        findNavController().navigate(R.id.EditCity, bundle)
    }

    private fun onDeleteClick(city: City){
        cityViewModel.deleteCity(city)
        Toast.makeText(context, "Registro eliminado", Toast.LENGTH_LONG).show()

    }
}