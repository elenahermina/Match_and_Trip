package com.example.matchtrip.Fragment


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.matchtrip.Adapter.TripAdapter
import com.example.matchtrip.R
import com.example.matchtrip.ViewModel.MainFragmentViewModel
import com.example.matchtrip.databinding.ActivityDescriptionBinding

class ActivityDescription: AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding
    private lateinit var viewModel: MainFragmentViewModel
    private  var adapter = TripAdapter()

    companion object {
        const val VALOR1 = "VALOR_1"
        const val VALOR2 = "VALOR_2"
        const val VALOR3 = "VALOR_3"
        const val VALOR4 = "VALOR_4"
        const val VALOR5 = "VALOR_5"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.activity_description))
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(VALOR1)
        val imageId = intent.getStringExtra(VALOR2)
        // val dates = intent.getStringExtra(VALOR3)
        // val subtitle = intent.getStringExtra(VALOR4)
        //  val intinerary = intent.getStringExtra(VALOR5)
    }
}