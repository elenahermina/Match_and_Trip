package com.example.matchtrip.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchtrip.Trip
import com.example.matchtrip.adapter.TripAdapter
import com.example.matchtrip.databinding.FragmentMainBinding
import com.example.matchtrip.viewModel.MainFragmentViewModel

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private var adapter = TripAdapter()
    private lateinit var model : MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this).get(MainFragmentViewModel::class.java)



    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createRecyclerView()

        model.tripPhotosList.observe(viewLifecycleOwner){
            updateTrip(it)
        }
    }

    override fun onResume() {
        super.onResume()
        model.getAllTrip()
    }




    fun createRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = adapter
    }

    private fun updateTrip(list:List<Trip>) {
        adapter.updateData(list)
    }

}