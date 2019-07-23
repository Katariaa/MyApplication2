package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable
import com.example.myapplication.utils.AdapterUtils.NewsConstants
import com.example.myapplication.utils.AdapterUtils.ViewType

data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
) : ViewType , Parcelable {
    override fun getItemViewType(): Int {
        return NewsConstants.News
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeInt(numComments)
        parcel.writeLong(created)
        parcel.writeString(thumbnail)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RedditNewsItem> {
        override fun createFromParcel(parcel: Parcel): RedditNewsItem {
            return RedditNewsItem(parcel)
        }

        override fun newArray(size: Int): Array<RedditNewsItem?> {
            return arrayOfNulls(size)
        }
    }
}