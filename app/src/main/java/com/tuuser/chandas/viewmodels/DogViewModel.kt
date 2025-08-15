package com.tuuser.chandas.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tuuser.chandas.data.DogRepository
import com.tuuser.chandas.models.Dog
import kotlinx.coroutines.flow.Flow

class DogViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: DogRepository
    val top5Dogs: Flow<List<Dog>>
    
    init {
        repository = DogRepository(application)
        top5Dogs = repository.dogs
    }
    
    fun addRatingToDog(dog: Dog, newRating: Float) {
        repository.addRatingToDog(dog, newRating)
    }
    
    fun updateDog(dog: Dog) {
        repository.updateDog(dog)
    }
    
    fun toggleFavorite(dog: Dog) {
        repository.toggleFavorite(dog)
    }
}
