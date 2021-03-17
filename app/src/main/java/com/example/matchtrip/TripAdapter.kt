package com.example.matchtrip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrip.databinding.TripLayoutBinding

class TripAdapter : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {
    lateinit var binding : TripLayoutBinding

    class TripViewHolder(var root: View, var tvName: TextView, var iwPhoto: ImageView) : RecyclerView.ViewHolder(root)

    private var tripList =  mutableListOf<Trip>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        binding = TripLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return TripViewHolder(binding.root, binding.tvName, binding.iwPhoto )
    }

    override fun getItemCount(): Int {
        return tripList.size
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip = tripList[position]
        holder.iwPhoto.setImageResource(trip.photo)
        holder.tvName.text = trip.name
    }

    fun updateData(list : MutableList<Trip>){
        tripList = list
        notifyDataSetChanged()
    }

}