package edu.skku.map.pa_practice.utils

import edu.skku.map.pa_practice.R

object logoUtil {
    fun getLogoResource(airlineName: String): Int {
        return when (airlineName) {
            "Burgerking" -> R.drawable.burger_king_logo
            "Mc' Donald" -> R.drawable.mc_logo
            "TwoSome!" -> R.drawable.twosome_logo
            else -> R.drawable.default_air_logo // 기본 로고 (없을 경우 대체)
        }
    }
}