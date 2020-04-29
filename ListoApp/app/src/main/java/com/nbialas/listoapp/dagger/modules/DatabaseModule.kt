package com.nbialas.listoapp.dagger.modules

import androidx.room.Room
import com.nbialas.listoapp.App
import com.nbialas.listoapp.db.ThingDatabase
import com.nbialas.listoapp.db.ThingToDoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule(private val application: App) {
    @Singleton
    @Provides
    fun provideRoomDatabase(): ThingDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ThingDatabase::class.java,
            "Database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideThingDao(database: ThingDatabase): ThingToDoDao {
        return database.thingDao()
    }
}