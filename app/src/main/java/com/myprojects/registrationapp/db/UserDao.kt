package com.myprojects.registrationapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
   /* @Query("DELETE FROM user_table")
    suspend fun deleteAll()*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

   @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user_table WHERE email = :email")
    fun getDataByEmail(email: String): Flow<Array<User>>

   /* @Query("SELECT password FROM user_table WHERE email = :email")
    fun getPasswordByEmail(email: String): Flow<String>*/

}