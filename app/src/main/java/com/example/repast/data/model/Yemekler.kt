package com.example.repast.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "foods")
data class Yemekler(


    @SerializedName("yemek_id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    var yemek_id: Int,

    @SerializedName("yemek_adi")
    @Expose
    val yemek_adi: String,

    @SerializedName("yemek_fiyat")
    @Expose
    val yemek_fiyat: String,

    @SerializedName("yemek_resim_adi")
    @Expose
    val yemek_resim_adi: String,

    ) : Parcelable{
        var count = 0
    }