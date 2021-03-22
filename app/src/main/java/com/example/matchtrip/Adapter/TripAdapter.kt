package com.example.matchtrip.Adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrip.ActivityDescription
import com.example.matchtrip.Trip
import com.example.matchtrip.databinding.TripLayoutBinding


class TripAdapter : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {

    private var tripList = listOf<Trip>()



    class TripViewHolder(val tripBinding: TripLayoutBinding) : RecyclerView.ViewHolder(tripBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
       val tripBinding = TripLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return TripViewHolder(tripBinding)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
     val trip = tripList[position]

        holder.tripBinding.iwPhoto.setImageResource(trip.photoId)
        holder.tripBinding.tvName.text = trip.name
       holder.tripBinding.iwPhoto.setOnClickListener {
           val intent = Intent(holder.tripBinding.iwPhoto.context, ActivityDescription::class.java)
           intent.putExtra(ActivityDescription.VALOR1, trip.name)
           intent.putExtra(ActivityDescription.VALOR2, trip.photoId)
           holder.tripBinding.iwPhoto.context.startActivity(intent)
       }
    }
    override fun getItemCount(): Int {
        return tripList.size
    }
    fun updateData(tripList : List<Trip>){
        this.tripList = tripList
        notifyDataSetChanged()
    }
}