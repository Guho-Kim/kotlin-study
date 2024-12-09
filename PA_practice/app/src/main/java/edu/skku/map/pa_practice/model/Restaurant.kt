package edu.skku.map.pa_practice.model

data class Restaurant(
    val restaurant_id: String,
    val restaurant_name: String,
    val location: String,
    val rating: Int,
    val open_time: String,
    val close_time: String,
    val reservation_user: List<Reservation>,
    val menu: List<Menu>
)