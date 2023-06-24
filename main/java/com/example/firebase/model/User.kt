package com.example.firebase.model

data class User(
    var id :String,
    var name:String,
    var email:String,
    var creteAt:Long=System.currentTimeMillis()
)
