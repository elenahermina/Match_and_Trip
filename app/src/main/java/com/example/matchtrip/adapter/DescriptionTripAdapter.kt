package com.example.matchtrip.adapter


import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrip.Photos
import com.example.matchtrip.databinding.PhotoDescriptionBinding


class DescriptionTripAdapter: RecyclerView.Adapter<DescriptionTripAdapter.DescriptionViewHolder>() {
   lateinit var binding: PhotoDescriptionBinding

    class DescriptionViewHolder(root: View, var tvIntro: TextView, var ivIzquerda: ImageView, var ivDerecha: ImageView): RecyclerView.ViewHolder(root)
    private var images = listOf<Photos>()

   fun setPhotos(list: List<Photos>){
       images = list
       notifyDataSetChanged()
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionViewHolder{
        binding = PhotoDescriptionBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DescriptionViewHolder(binding.root, binding.tvIntro, binding.ivDerecha, binding.ivIzquierda)
    }
    override fun onBindViewHolder(holder: DescriptionViewHolder, position: Int) {

        holder.tvIntro.text = images[position].photoName

        if(position %2 == 0){
            holder.ivDerecha.visibility =View.GONE
            holder.ivIzquerda.visibility = View.VISIBLE
            holder.ivIzquerda.setImageResource(images[position].imageId)
            holder.tvIntro.gravity = Gravity.END


        } else {
            holder.ivIzquerda.visibility= View.GONE
            holder.ivDerecha.visibility = View.VISIBLE
            holder.ivDerecha.setImageResource(images[position].imageId)
            holder.tvIntro.gravity = Gravity.START
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

}