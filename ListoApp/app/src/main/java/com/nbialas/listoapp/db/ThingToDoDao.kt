package com.nbialas.listoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nbialas.listoapp.models.ThingToDo

@Dao
interface ThingToDoDao {
    @Query("SELECT * FROM thingToDoData")
    fun getAll(): LiveData<List<ThingToDo>>

    @Query("SELECT  * FROM thingToDoData  WHERE uniqueID =:id ")
    fun getSingleThing(id: String): LiveData<ThingToDo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveThingToDo(thingToDo: ThingToDo)

    @Query("UPDATE thingToDoData SET openCounter = openCounter  + 1  WHERE uniqueID = :id")
    suspend fun updateThingToDo(id: String): Int

    @Delete
    suspend fun delete(thingToDo: ThingToDo)
}