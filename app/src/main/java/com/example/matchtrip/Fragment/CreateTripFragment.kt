package com.example.matchtrip.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.matchtrip.activity.MenuActivityInterface
import com.example.matchtrip.databinding.CreateTripBinding
import com.example.matchtrip.databinding.CreateUserLayoutBinding
import com.example.matchtrip.viewModel.CreateTripFragmentViewModel
import com.example.matchtrip.viewModel.CreateUserFragmentViewModel

class CreateTripFragment(var menuActivityInterface: MenuActivityInterface): Fragment() {

    private lateinit var binding: CreateTripBinding
    private lateinit var model: CreateTripFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this).get(CreateTripFragmentViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateTripBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun createTrip(){

        val tripType = binding.tripName.text.trim()
        val destination = binding.addDescription.text.trim()

        binding.calendar.date = System.currentTimeMillis()




    }
}