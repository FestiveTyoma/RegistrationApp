package com.myprojects.registrationapp

import android.app.Application
import com.myprojects.registrationapp.db.UserRepository
import com.myprojects.registrationapp.db.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class UserApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { UserRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserRepository(database.userDao(), email = String()) }
}