package com.tuuser.chandas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuuser.chandas.R
import com.tuuser.chandas.adapters.PhotoGridAdapter
import com.tuuser.chandas.models.Photo

class ProfileFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoGridAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recyclerView = view.findViewById(R.id.recyclerViewPhotos)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // 2 columnas
        
        // Datos dummy para las fotos
        val photos = listOf(
            Photo(R.drawable.perro1, 5, "Foto en el parque"),
            Photo(R.drawable.perro2, 3, "Foto en casa"),
            Photo(R.drawable.perro3, 7, "Foto jugando"),
            Photo(R.drawable.perro4, 4, "Foto durmiendo"),
            Photo(R.drawable.perro5, 6, "Foto comiendo"),
            Photo(R.drawable.perro6, 8, "Foto de paseo"),
            Photo(R.drawable.perro1, 2, "Foto con amigos"),
            Photo(R.drawable.perro2, 5, "Foto en la playa")
        )
        
        photoAdapter = PhotoGridAdapter(photos)
        recyclerView.adapter = photoAdapter
    }
}
