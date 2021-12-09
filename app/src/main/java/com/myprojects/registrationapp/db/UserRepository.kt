package com.myprojects.registrationapp.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

   fun userByEmail(email:String): Flow<User> = userDao.getDataByEmail(email)

    val getAll: Flow<List<User>> =  userDao.getAll()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}