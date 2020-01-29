package com.example.mvvmandroid.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmandroid.model.Post
import com.example.mvvmandroid.model.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}