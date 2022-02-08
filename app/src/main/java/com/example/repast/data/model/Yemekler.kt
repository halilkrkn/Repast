package com.example.repast.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Yemekler(

    @SerializedName("yemek_id")
    @Expose
    val yemek_id: String,

    @SerializedName("yemek_adi")
    @Expose
    val yemek_adi: String,

    @SerializedName("yemek_fiyat")
    @Expose
    val yemek_fiyat: String,

    @SerializedName("yemek_resim_adi")
    @Expose
    val yemek_resim_adi: String,

    ) : Parcelable