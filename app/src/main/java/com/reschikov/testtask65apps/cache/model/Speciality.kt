package com.reschikov.testtask65apps.cache.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val EMPTY_STRING = ""

@Entity(tableName = "speciality")
data class Speciality (@PrimaryKey val id : Int, val name : String) : Parcelable {

    companion object CREATOR : Parcelable.Creator<Speciality> {
        override fun createFromParcel(parcel: Parcel): Speciality {
            return Speciality(parcel)
        }

        override fun newArray(size: Int): Array<Speciality?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: EMPTY_STRING
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }
}