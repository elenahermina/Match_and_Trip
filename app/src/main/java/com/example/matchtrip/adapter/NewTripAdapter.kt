package com.example.matchtrip.adapter


import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrip.Photos
import com.example.matchtrip.databinding.AdPhotoBinding




class NewTripAdapter: RecyclerView.Adapter<NewTripAdapter.TripViewHolder>() {

    lateinit var binding: AdPhotoBinding
    private var photosList = listOf<Photos>()

    class TripViewHolder(val binding: AdPhotoBinding ) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
         binding = AdPhotoBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return TripViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
     val photos = photosList[position]


      photos.image?.let{holder.binding.iwPhoto.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size))  }
       photos.photoName?.let{holder.binding.tvName.setText(photos.photoName)}


    }
    override fun getItemCount(): Int {
        return photosList.size
    }
    fun updateData(photos: List<Photos>){

        this.photosList = photos
        notifyDataSetChanged()
    }
}

