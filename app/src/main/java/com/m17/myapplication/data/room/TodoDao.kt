package com.m17.myapplication.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAllTodos(): LiveData<List<Todo>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(debt: Todo)
    @Update
    fun updateTodo(debt: Todo)
    @Delete
    fun deleteTodo(debt: Todo)
    @Query("DELETE FROM Todo")
    fun deleteAllTodos()

}