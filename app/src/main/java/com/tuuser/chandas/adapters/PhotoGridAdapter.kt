package com.tuuser.chandas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tuuser.chandas.R
import com.tuuser.chandas.models.Photo

class PhotoGridAdapter(private val photos: List<Photo>) : 
    RecyclerView.Adapter<PhotoGridAdapter.PhotoViewHolder>() {
    
    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewPhoto)
        val textViewBones: TextView = itemView.findViewById(R.id.textViewBones)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo_grid, parent, false)
        return PhotoViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        
        holder.imageView.setImageResource(photo.imageResId)
        holder.textViewBones.text = "🦴 ${photo.boneCount}"
    }
    
    override fun getItemCount() = photos.size
}
