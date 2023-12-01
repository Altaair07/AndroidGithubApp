package com.dicoding.androidgithubappsub2.data.repository

import com.dicoding.androidgithubappsub2.data.local.entity.UserEntity
import com.dicoding.androidgithubappsub2.data.local.room.UserDao
import com.dicoding.androidgithubappsub2.data.remote.RemoteDataSource
import com.dicoding.androidgithubappsub2.data.remote.response.DetailResponse
import com.dicoding.androidgithubappsub2.data.remote.response.SearchResponse
import com.dicoding.androidgithubappsub2.data.remote.response.UserResponse
import com.dicoding.androidgithubappsub2.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val userDao: UserDao,
) : UserRepository {

    override fun getAllUsers(): Flow<Result<List<UserResponse>>> {
        return flow {
            emit(Result.Loading())
            try {
                val result = remoteDataSource.getAllUsers()
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }
    }

    override fun searchUser(username: String): Flow<Result<SearchResponse>> {
        return flow {
            emit(Result.Loading())
            try {
                val result = remoteDataSource.searchUser(username)
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }
    }

    override fun getDetailUser(username: String): Flow<Result<DetailResponse>> {
        return flow {
            emit(Result.Loading())
            try {
                val result = remoteDataSource.getDetailUser(username)
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }
    }

    override fun getFollowings(username: String): Flow<Result<List<UserResponse>>> {
        return flow {
            emit(Result.Loading())
            try {
                val result = remoteDataSource.getFollowings(username)
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }
    }

    override fun getFollowers(username: String): Flow<Result<List<UserResponse>>> {
        return flow {
            emit(Result.Loading())
            try {
                val result = remoteDataSource.getFollowers(username)
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }
    }

    override suspend fun addToFavorite(userEntity: UserEntity) {
        userDao.addToFavorite(userEntity)
    }

    override fun getAllFavoriteUsers(): Flow<List<UserEntity>> {
        return userDao.getAllFavoriteUsers()
    }

    override fun isFavoriteUser(username: String): Flow<Boolean> {
        return userDao.isFavoriteUser(username)
    }

    override suspend fun deleteUser(username: String) {
        userDao.deleteUser(username)
    }
}