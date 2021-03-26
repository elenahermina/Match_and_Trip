package com.example.matchtrip.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrip.Photos
import com.example.matchtrip.Trip
import com.example.matchtrip.activity.TripDescriptionActivity
import com.example.matchtrip.databinding.AdPhotoBinding

class NewTripAdapter: RecyclerView.Adapter<NewTripAdapter.TripViewHolder>() {

    private var photosList = listOf<Photos>()

    class TripViewHolder(val adPhotoBinding:  AdPhotoBinding) : RecyclerView.ViewHolder(adPhotoBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val adPhotoBinding = AdPhotoBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return TripViewHolder(adPhotoBinding)
    }
    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val photos = photosList[position]

        holder.adPhotoBinding.iwPhoto.setImageResource(photos.imageId)
        holder.adPhotoBinding.tvName.setText(photos.photoName)

    }
    override fun getItemCount(): Int {
        return photosList.size
    }
    fun updateData(photos: List<Photos>){

        this.photosList = photos
        notifyDataSetChanged()
    }
}