package com.tuuser.chandas.models

data class Dog(
    val id: Int = 0,
    val name: String,
    val isFavorite: Boolean,
    val imageResId: Int,
    val backgroundResId: Int,
    val rating: Float,
    val timestamp: Long = System.currentTimeMillis()
)
