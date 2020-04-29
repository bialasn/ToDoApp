package com.nbialas.listoapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nbialas.listoapp.models.ThingToDo

@Dao
interface ThingToDoDao {
    @Query("SELECT * FROM thingToDoData")
    fun getAllThingsToDo(): LiveData<List<ThingToDo>>

    @Query("SELECT  * FROM thingToDoData  WHERE uniqueID =:id ")
    fun getSingleThing(id: String): LiveData<ThingToDo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThingToDo(thingToDo: ThingToDo)

    @Query("UPDATE thingToDoData SET openCounter = openCounter  + 1  WHERE uniqueID = :id")
    suspend fun updateThingToDo(id: String): Int

    @Delete
    suspend fun delete(thingToDo: ThingToDo)
}