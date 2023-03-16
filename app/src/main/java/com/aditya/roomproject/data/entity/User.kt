package com.aditya.roomproject.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "first_name") val fullName: String?,
    @ColumnInfo(name = "last_name") val email: String?,
    @ColumnInfo(name = "phone") val phone: String?

)