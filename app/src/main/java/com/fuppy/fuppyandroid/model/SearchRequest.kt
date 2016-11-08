package com.fuppy.fuppyandroid.model

import android.os.Parcel
import android.os.Parcelable

/**
 * This is a data class for encapsulating the findPets API call
 */
data class SearchRequest(var location: String = "", var type: String = "", var breed: String = "",
                          var size: String = "", var sex: String = "", var age: String = "") : Parcelable {

    constructor(source: Parcel): this(source.readString(), source.readString(), source.readString(),
            source.readString(), source.readString(), source.readString())

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(location)
        dest.writeString(type)
        dest.writeString(breed)
        dest.writeString(size)
        dest.writeString(sex)
        dest.writeString(age)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<SearchRequest> = object : Parcelable.Creator<SearchRequest> {
            override fun createFromParcel(source: Parcel): SearchRequest{
                return SearchRequest(source)
            }

            override fun newArray(size: Int): Array<SearchRequest?> {
                return arrayOfNulls(size)
            }
        }
    }
}