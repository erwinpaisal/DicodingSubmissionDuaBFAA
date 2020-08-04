package com.erwin.subdua.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataPengguna(
    var username: String? = null,
    var name: String? = null,
    var avatar: String? = null,
    var company: String? = null,
    var location: String? = null,
    var repository: String? = null,
    var followers: String? = null,
    var following: String? = null
): Parcelable