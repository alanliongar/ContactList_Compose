package com.example.contactlist_compose

import androidx.annotation.DrawableRes

data class Contact(
    val id: Int,
    val name: String,
    val tel: String,
    @DrawableRes val image: Int,
)
