package com.example.firebasekotlincrud
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Users(val Id: String? = null,val Name: String? = null, val Email: String? = null, val Role: String? = null) {
}