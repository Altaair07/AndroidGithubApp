package com.dicoding.androidgithubappsub2.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.androidgithubappsub2.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorite(userEntity: UserEntity)

    @Query("SELECT * FROM userentity ORDER BY id DESC")
    fun getAllFavoriteUsers(): Flow<List<UserEntity>>

    @Query("DELETE FROM userentity WHERE login = :username")
    suspend fun deleteUser(username: String)

    @Query("SELECT EXISTS(SELECT * FROM userentity WHERE login = :username)")
    fun isFavoriteUser(username: String): Flow<Boolean>
}