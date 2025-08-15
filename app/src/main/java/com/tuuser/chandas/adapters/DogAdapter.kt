package com.tuuser.chandas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tuuser.chandas.R
import com.tuuser.chandas.models.Dog

class DogAdapter(
    private var dogs: List<Dog>,
    private val onRatingClick: (Dog, Float) -> Unit
) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
    
    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewDog)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewRating: TextView = itemView.findViewById(R.id.textViewRating)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogs[position]
        
        holder.imageView.setImageResource(dog.imageResId)
        holder.textViewName.text = dog.name
        holder.textViewRating.text = "🦴 ${dog.rating}"
        
        // Hacer el rating clickeable para incrementar
        holder.textViewRating.setOnClickListener {
            val newRating = (dog.rating + 0.1f).coerceAtMost(5.0f)
            onRatingClick(dog, newRating)
        }
    }
    
    override fun getItemCount() = dogs.size
    
    fun updateDogs(newDogs: List<Dog>) {
        dogs = newDogs
        notifyDataSetChanged()
    }
}
