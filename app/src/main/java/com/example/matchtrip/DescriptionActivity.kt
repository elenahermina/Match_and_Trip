package com.example.matchtrip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.matchtrip.databinding.ActivityMainBinding
import com.example.matchtrip.databinding.DescriptionTripBinding

class DescriptionActivity: AppCompatActivity() {
    lateinit var binding: DescriptionTripBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DescriptionTripBinding.inflate(layoutInflater)
        setContentView(binding.root)



        }
}