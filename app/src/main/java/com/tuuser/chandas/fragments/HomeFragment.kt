package com.tuuser.chandas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuuser.chandas.adapters.DogAdapter
import com.tuuser.chandas.viewmodels.DogViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private val viewModel: DogViewModel by activityViewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.tuuser.chandas.R.layout.fragment_home, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recyclerView = view.findViewById(com.tuuser.chandas.R.id.recyclerViewDogs)
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        dogAdapter = DogAdapter(emptyList()) { dog, newRating ->
            viewModel.addRatingToDog(dog, newRating)
        }
        recyclerView.adapter = dogAdapter
        
        // Observar cambios en la base de datos
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.top5Dogs.collect { dogs ->
                dogAdapter.updateDogs(dogs)
            }
        }
    }
}
