package com.example.basedatal.dao

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.basedatal.entities.City

@Database(entities = [City::class], version=1)

abstract class AppDataBase: RoomDatabase() {
    abstract fun cityDao():CityDao
}