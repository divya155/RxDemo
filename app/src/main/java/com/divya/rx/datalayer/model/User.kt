package com.divya.rx.datalayer.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("gender")
    var gender: String,

    @SerializedName("email")
    var email: String
){

    fun name(): String{
        return "$firstName  $lastName"
    }
}
