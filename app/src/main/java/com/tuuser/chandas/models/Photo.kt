package com.tuuser.chandas.models

import androidx.annotation.DrawableRes

data class Photo(
    @DrawableRes val imageResId: Int,
    val boneCount: Int,
    val description: String
)
