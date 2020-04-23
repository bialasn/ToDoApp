package com.nbialas.listoapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thingToDoData")
class ThingToDo(
    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey
    @ColumnInfo(name = "uniqueID")
    val uniqueID: String,

    @ColumnInfo(name = "creationDate")
    val creationDate: Long,

    @ColumnInfo(name = "openCounter")
    var openCounter: Int,

    @ColumnInfo(name = "color")
    val color: Int

)
