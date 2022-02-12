package com.example.repast.data.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SepetYemekler(

    @SerializedName("yemek_adi")
    @Expose
    val yemek_adi: String,

    @SerializedName("yemek_resim_adi")
    @Expose
    val yemek_resim_adi: String,

    @SerializedName("kullanici_adi")
    @Expose
    val kullanici_adi: String,

    @SerializedName("yemek_fiyat")
    @Expose
    val yemek_fiyat: String,

    @SerializedName("yemek_siparis_adet")
    @Expose
    val yemek_siparis_adet: String,

    @SerializedName("sepet_yemek_id")
    @Expose
    val sepet_yemek_id: String

) : Parcelable