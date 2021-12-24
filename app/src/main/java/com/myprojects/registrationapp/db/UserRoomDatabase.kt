package com.myprojects.registrationapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
public abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Используется паттерн синглтон для одного экземпляра класса базы данных
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UserRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(UserDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class UserDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.userDao())
                    }
                }
            }
        }


        suspend fun populateDatabase(wordDao: UserDao) {
           /* wordDao.deleteAll()
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)*/
        }
    }
}