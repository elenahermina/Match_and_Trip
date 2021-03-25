package com.example.matchtrip.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchtrip.R
import com.example.matchtrip.Trip
import com.example.matchtrip.adapter.DescriptionTripAdapter
import com.example.matchtrip.databinding.ActivityDescriptionBinding
import com.example.matchtrip.viewModel.TripDescriptionActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TripDescriptionActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding
    private lateinit var model: TripDescriptionActivityViewModel
    private  var adapter = DescriptionTripAdapter()

    companion object {
        const val ID_TRIP1 = "VALOR_1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(TripDescriptionActivityViewModel::class.java)

        model.tripDetail.observe(this){trip ->
            binding.iwPhoto.setImageResource(trip.tripPhotoId)
            binding.tvTitle.text = trip.name
            binding.tvDetail.text =trip.details
            binding.tvDates.text = trip.dates
            binding.tvIntinerary.text = trip.intinerary
            trip.map?.let {  binding.iwPhotoThai.setImageResource(it) }
            trip.review?.let { binding.review.setImageResource(it) }
            lifecycleScope.launch(Dispatchers.Main){
               var listaPhotos = model.cargarPhotos(trip)
                adapter.setPhotos(listaPhotos)

            }


        }

        var idTrip =  intent.getLongExtra(ID_TRIP1,0)
        model.tripDetails(idTrip)

        createRecyclerView()



    }
    fun createRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = adapter
    }
}




