package com.myprojects.registrationapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User( val name:String, val surname:String, val age:String,
                val mobile:String, val email:String, val password:String ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

