package com.aditya.roomproject.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int?= null,
    @ColumnInfo(name = "first_name") var fullName: String?,
    @ColumnInfo(name = "last_name") var email: String?,
    @ColumnInfo(name = "phone") var phone: String?

)