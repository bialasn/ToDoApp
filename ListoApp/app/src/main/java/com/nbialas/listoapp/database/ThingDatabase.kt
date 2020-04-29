package com.nbialas.listoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nbialas.listoapp.database.dao.ThingToDoDao
import com.nbialas.listoapp.models.ThingToDo

@Database(
    entities = [ThingToDo::class], version = 1
)
abstract class ThingDatabase : RoomDatabase() {
    abstract fun thingDao(): ThingToDoDao
}