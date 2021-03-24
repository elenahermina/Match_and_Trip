package com.example.matchtrip.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrip.activity.TripDescriptionActivity
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
            val intent = Intent(holder.tripBinding.iwPhoto.context, TripDescriptionActivity::class.java)
            intent.putExtra(TripDescriptionActivity.ID_TRIP1, trip.id)

            holder.tripBinding.iwPhoto.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return tripList.size
    }
    fun updateData(tripList: List<Trip>){
        this.tripList = tripList
        notifyDataSetChanged()
    }
}