package com.aditya.roomproject.data.dao

import androidx.room.*
import com.aditya.roomproject.data.entity.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user") //untuk mengambil semua data
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)") //untuk mengambil data berdasarkan user id
    fun loadAllByIds(userIds: IntArray): List<User>

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("Select * FROM user WHERE uid = :uid")
    fun get(uid: Int) : User

    @Update
    fun update(user: User)
}