package com.myprojects.registrationapp.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao, email: String) {

   val userByEmail: Flow<Array<User>> = userDao.getDataByEmail(email)

    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}