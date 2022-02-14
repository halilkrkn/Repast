package com.example.repast.data.model


import com.google.gson.annotations.SerializedName

data class CartFoodResponse(
    @SerializedName("sepet_yemekler")
    val sepetYemekler: List<SepetYemekler>,
    val success: Int
)