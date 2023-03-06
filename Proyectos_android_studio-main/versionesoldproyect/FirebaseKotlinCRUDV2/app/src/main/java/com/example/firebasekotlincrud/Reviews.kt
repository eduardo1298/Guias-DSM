package com.example.firebasekotlincrud
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Reviews(var UserName: String? = null, var Review: String? = null) {
}