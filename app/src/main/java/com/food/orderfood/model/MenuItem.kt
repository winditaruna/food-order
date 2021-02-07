package com.food.orderfood.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuItem(
    var namaMenu: String? = "",
    var harga: Int = 0,
    var jenis: String? = "",
    var gambar: String? = ""
) : Parcelable