package com.example.kursacho.data

data class UserData(
    var userId: String?="",
    var name: String?="",
    var number: String?="",
    var imageUrl: String?="",
){

    fun toMap()= mapOf(
        "userId" to userId,
        "name" to name,
        "number" to number,
        "imageUrl" to imageUrl,
    )

}