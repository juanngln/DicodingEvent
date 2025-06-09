package com.idcamp.dicodingevent.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteEvent(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "media_cover")
    var mediaCover: String? = null,

    @ColumnInfo(name = "image_logo")
    var imageLogo: String? = null,

    @ColumnInfo(name = "summary")
    var summary: String = "",

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "link")
    var link: String? = null
) : Parcelable
