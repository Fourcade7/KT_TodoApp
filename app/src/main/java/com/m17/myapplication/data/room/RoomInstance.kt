package com.m17.myapplication.data.room

import com.m17.myapplication.uiutils.db

object RoomInstance {

    var todoDao: TodoDao=db.todoDao()

}