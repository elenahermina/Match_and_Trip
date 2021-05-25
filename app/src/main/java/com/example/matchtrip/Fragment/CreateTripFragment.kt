package com.example.matchtrip.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchtrip.Trip
import com.example.matchtrip.activity.MenuActivityInterface
import com.example.matchtrip.adapter.NewTripAdapter
import com.example.matchtrip.databinding.CreateTripBinding
import com.example.matchtrip.viewModel.CreateTripFragmentViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateTripFragment(var menuActivityInterface: MenuActivityInterface) : Fragment() {

    private lateinit var binding: CreateTripBinding
    private lateinit var model: CreateTripFragmentViewModel
    private var adapter = NewTripAdapter()

    private var fechaInicio = 0L
    private var fechaFin = 0L

    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this).get(CreateTripFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateTripBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRecyclerView()

        initListener()
    }

    private fun initListener() {
        binding.addPhotos.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)
        }

        binding.etCalendar.setOnFocusChangeListener { view, hasFocus ->
            Log.w("TEST", "setOnFocusChange")
            if (hasFocus) {
                val builder = MaterialDatePicker.Builder.dateRangePicker()
                builder.setSelection(
                    androidx.core.util.Pair(
                        System.currentTimeMillis(),
                        System.currentTimeMillis()
                    )
                )
                val picker = builder.build()
                picker.show(requireActivity().supportFragmentManager, picker.toString())
                picker.addOnNegativeButtonClickListener { picker.dismiss() }
                picker.addOnPositiveButtonClickListener {
                    val calendar1 = Calendar.getInstance()
                    val calendar2 = Calendar.getInstance()

                    val date1 = it.first?.let { it1 -> Date(it1) }
                    val date2 = it.second?.let { it2 -> Date(it2) }
                    calendar1.time = date1
                    calendar2.time = date2

                    if (date1 != null && date2 != null) {
                        binding.etCalendar.setText(
                            "The selected date range is ${calendar1.get(Calendar.DATE)} - ${
                                calendar2.get(
                                    Calendar.DATE
                                )
                            }"
                        )
                        fechaFin = calendar2.timeInMillis
                        fechaInicio = calendar1.timeInMillis
                    }
                }
            }
        }
        binding.guardar.setOnClickListener {
            createTrip()
        }

    }

    private fun createTrip() {

        Log.w("TEST", "createTrip")
        val tripName = binding.tripName.text.trim()
        val destination = binding.addDescription.text.trim()

        if (tripName.isEmpty()) {
            binding.tripName.error = "Trip name is required"
            binding.tripName.requestFocus()
            return
        }
        if (destination.isEmpty()) {
            binding.destination.error = "Destination is required"
            binding.destination.requestFocus()
            return
        }

        writeNewTrip(tripName.toString(), destination.toString(), fechaInicio, fechaFin)
    }

    private fun writeNewTrip(
        tripName: String,
        destination: String,
        fechaInicio: Long,
        fechaFin: Long
    ) {
        val trip = Trip(tripName, tripPhotoId = null, fechaInicio, fechaFin, destination)

        lifecycleScope.launch {
            model.insertTrip(trip)
            menuActivityInterface.goHome()
        }
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                uri?.let { uri ->
                    requireActivity().contentResolver.let { contentResolver ->
                        bitmap =
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                                val source = ImageDecoder.createSource(contentResolver, uri)
                                ImageDecoder.decodeBitmap(source)
                            } else {
                                MediaStore.Images.Media.getBitmap(
                                    this.requireActivity().contentResolver,
                                    uri
                                )
                            }
                    }
                }
            }
        }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = adapter

    }

    private fun formatDate(date: Date): String {
        var simpleDateFormat = SimpleDateFormat("dd.mm.yy")
        return simpleDateFormat.format(date.time)
    }
}