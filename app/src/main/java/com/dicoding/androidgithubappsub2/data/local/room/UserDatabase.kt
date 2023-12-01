package com.dicoding.androidgithubappsub2.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.androidgithubappsub2.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}