package com.food.orderfood.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String? = "",
    var nama: String? = "",
    var email: String? = ""
) : Parcelable