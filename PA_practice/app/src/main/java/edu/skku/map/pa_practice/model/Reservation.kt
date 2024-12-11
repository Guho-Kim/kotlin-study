package edu.skku.map.pa_practice.model

data class Reservation(
    val userId: String,
    val restaurantName: String,
    val numberOfPeople: Int,
    val reservationDate: String,
    val reservationTime: String
)
