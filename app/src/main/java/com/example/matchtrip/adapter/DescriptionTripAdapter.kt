package com.example.matchtrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrip.R
import com.example.matchtrip.Trip
import com.example.matchtrip.databinding.PhotoDescriptionBinding


class DescriptionTripAdapter: RecyclerView.Adapter<DescriptionTripAdapter.DescriptionViewHolder>() {
   lateinit var binding: PhotoDescriptionBinding

    class DescriptionViewHolder(root: View, var tvNombre: TextView, var tvIntro: TextView, var ivIzquerda: ImageView, var ivDerecha: ImageView): RecyclerView.ViewHolder(root)
    private var trips = mutableListOf<Trip>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionViewHolder{
        binding = PhotoDescriptionBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DescriptionViewHolder(binding.root, binding.tvName, binding.tvIntro, binding.ivDerecha, binding.ivIzquierda)
    }
    override fun onBindViewHolder(holder: DescriptionViewHolder, position: Int) {

        holder.tvIntro.text = trips[position].name
        holder.tvNombre.text =trips[position].name
        if(position %6 > 2){
            holder.ivIzquerda.visibility =View.GONE
            holder.ivIzquerda.visibility = View.VISIBLE

        } else {
            holder.ivIzquerda.visibility= View.VISIBLE
            holder.ivDerecha.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return trips.size
    }

}