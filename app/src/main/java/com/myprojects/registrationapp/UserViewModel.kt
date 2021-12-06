package com.myprojects.registrationapp.viewModel

import android.app.Application
import androidx.lifecycle.*

import com.myprojects.registrationapp.db.User
import com.myprojects.registrationapp.db.UserRepository
import com.myprojects.registrationapp.db.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

 class UserViewModel( private val repository: UserRepository) : ViewModel() {

     val userByEmail: LiveData<Array<User>> = repository.userByEmail.asLiveData()


   fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
    }
class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
