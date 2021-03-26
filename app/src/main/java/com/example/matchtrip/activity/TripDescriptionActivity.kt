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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TripDescriptionActivity: AppCompatActivity() , OnMapReadyCallback {

    private lateinit var binding: ActivityDescriptionBinding
    private lateinit var model: TripDescriptionActivityViewModel
    private  var adapter = DescriptionTripAdapter()

    private lateinit var mMap: GoogleMap

    companion object {
        const val ID_TRIP1 = "VALOR_1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(TripDescriptionActivityViewModel::class.java)


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        model.tripDetail.observe(this){trip ->
            trip.tripPhotoId?.let { binding.iwPhoto.setImageResource(it) }
            binding.tvTitle.text = trip.name
            binding.tvDetail.text =trip.details
            binding.tvDates.text = "${trip.datesInicio.toString()} - ${trip.datesFinal.toString()}"
            binding.tvIntinerary.text = trip.intinerary
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val latLngMadrid = LatLng(40.4167, -3.70325)
        mMap.addMarker(MarkerOptions().position(latLngMadrid).title("Marker en Madrid"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngMadrid))
    }
}




