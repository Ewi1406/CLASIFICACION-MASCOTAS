package com.tuuser.chandas.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tuuser.chandas.models.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DogRepository(context: Context) {
    
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("dogs_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs: Flow<List<Dog>> = _dogs.asStateFlow()
    
    init {
        loadDogs()
        if (_dogs.value.isEmpty()) {
            initializeSampleData()
        }
    }
    
    private fun loadDogs() {
        val dogsJson = sharedPreferences.getString("dogs", "[]")
        val type = object : TypeToken<List<Dog>>() {}.type
        val dogsList: List<Dog> = gson.fromJson(dogsJson, type) ?: emptyList()
        _dogs.value = dogsList.sortedByDescending { it.rating }.take(5)
    }
    
    private fun saveDogs() {
        val dogsJson = gson.toJson(_dogs.value)
        sharedPreferences.edit().putString("dogs", dogsJson).apply()
    }
    
    private fun initializeSampleData() {
        val sampleDogs = listOf(
            Dog(id = 1, name = "Luna", isFavorite = true, imageResId = com.tuuser.chandas.R.drawable.perro1, backgroundResId = com.tuuser.chandas.R.drawable.dog_golden_body, rating = 4.5f),
            Dog(id = 2, name = "Max", isFavorite = false, imageResId = com.tuuser.chandas.R.drawable.perro2, backgroundResId = com.tuuser.chandas.R.drawable.dog_golden_body, rating = 4.2f),
            Dog(id = 3, name = "Rocky", isFavorite = true, imageResId = com.tuuser.chandas.R.drawable.perro3, backgroundResId = com.tuuser.chandas.R.drawable.dog_puppy, rating = 4.8f),
            Dog(id = 4, name = "Bella", isFavorite = false, imageResId = com.tuuser.chandas.R.drawable.perro4, backgroundResId = com.tuuser.chandas.R.drawable.dog_pastor, rating = 4.0f),
            Dog(id = 5, name = "Toby", isFavorite = false, imageResId = com.tuuser.chandas.R.drawable.perro5, backgroundResId = com.tuuser.chandas.R.drawable.dog_pug, rating = 4.3f)
        )
        _dogs.value = sampleDogs
        saveDogs()
    }
    
    fun addRatingToDog(dog: Dog, newRating: Float) {
        val currentDogs = _dogs.value.toMutableList()
        val index = currentDogs.indexOfFirst { it.id == dog.id }
        
        if (index != -1) {
            val updatedDog = dog.copy(rating = newRating, timestamp = System.currentTimeMillis())
            currentDogs[index] = updatedDog
        } else {
            val newDog = dog.copy(rating = newRating, timestamp = System.currentTimeMillis())
            currentDogs.add(newDog)
        }
        
        // Mantener solo las top 5 mascotas ordenadas por rating
        _dogs.value = currentDogs.sortedByDescending { it.rating }.take(5)
        saveDogs()
    }
    
    fun updateDog(dog: Dog) {
        val currentDogs = _dogs.value.toMutableList()
        val index = currentDogs.indexOfFirst { it.id == dog.id }
        
        if (index != -1) {
            currentDogs[index] = dog
            _dogs.value = currentDogs.sortedByDescending { it.rating }.take(5)
            saveDogs()
        }
    }
    
    fun toggleFavorite(dog: Dog) {
        val updatedDog = dog.copy(isFavorite = !dog.isFavorite)
        updateDog(updatedDog)
    }
}
