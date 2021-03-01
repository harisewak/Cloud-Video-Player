package com.harisewak.cloudvideoplayer.video

import android.os.Parcel
import android.os.Parcelable

data class VideoModel(
    val id: String,
    val title: String,
    val s3Url: String,
    val cfUrl: String
) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<VideoModel> {
            override fun createFromParcel(parcel: Parcel) = VideoModel(parcel)
            override fun newArray(size: Int) = arrayOfNulls<VideoModel>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        id = parcel.readString()!!,
        title = parcel.readString()!!,
        s3Url = parcel.readString()!!,
        cfUrl = parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(s3Url)
        parcel.writeString(cfUrl)
    }

    override fun describeContents() = 0

}