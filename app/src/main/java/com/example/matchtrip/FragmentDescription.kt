package com.example.matchtrip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrip.Adapter.TripAdapter
import com.example.matchtrip.ViewModel.MainActivityViewModel
import com.example.matchtrip.databinding.ActivityMainBinding
import com.example.matchtrip.databinding.FragmentDescriptionBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class FragmentDescription: Fragment() {

    private lateinit var binding: FragmentDescriptionBinding
   private lateinit var model: MainActivityViewModel
   private  var adapter = TripAdapter()

    companion object {
        fun getFragment(): FragmentDescription {
            return FragmentDescription()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }


   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       createRecyclerView()

       arguments?.let {
           binding.tvTitle.text = it.getString(" Clave_1")
           binding.iwPhoto.setImageResource(it.getInt("Clave_2"))
           binding.tvDates.text = it.getString(" Clave_1")
           binding.tvDetail.text = it.getString(" Clave_1")
           binding.tvSubtitle.text = it.getString(" Clave_1")
           binding.tvIntinerary.text = it.getString(" Clave_1")
           binding.iwPhotoThai.setImageResource(it.getInt("Clave_2"))
       }
   }

       /*    activity?.let {
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(it.application)).get(MainActivityViewModel::class.java)
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.getAllTrip().value?.let { campus -> adapter.updateData(trip) }
                viewModel.getAllTrip().observe(this@FragmentDescription, { campus -> adapter.updateData(trip) })
            }
        }

            }*/



    private fun createRecyclerView() {
       // adapter = TripAdapter(listOf())
       binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.recyclerView.adapter = adapter
    }
}