package com.m17.myapplication.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize()
@Entity(tableName = "Todo")
class Todo constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val uid:Int=0,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "description")
    val description:String,
    @ColumnInfo(name = "dataTime")
    val dataTime:String,
    @ColumnInfo(name = "dataTime2")
    val dataTime2:String,
    @ColumnInfo(name = "priority")
    val priority:Int,
    @ColumnInfo(name = "category")
    val category:Int,
    @ColumnInfo(name = "status")
    val status:Boolean=false,
):Parcelable