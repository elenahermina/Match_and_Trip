package com.example.matchtrip.Activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchtrip.ActivityDescription
import com.example.matchtrip.ViewModel.MainActivityViewModel
import com.example.matchtrip.Trip
import com.example.matchtrip.Adapter.TripAdapter
import com.example.matchtrip.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private  var adapter = TripAdapter()
    private lateinit var model : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        createRecyclerView()

        model.tripList.observe(this){
            updateTrip(it)
        }
        model.getAllTrip()
    }
    fun createRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun updateTrip(students : List<Trip>){
        adapter.updateData(students)
    }
}


