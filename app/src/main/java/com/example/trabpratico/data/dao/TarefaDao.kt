package com.example.trabpratico.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.trabpratico.data.entities.Tarefa

@Dao
interface TarefaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTarefa(tarefa: Tarefa)

    @Update
    suspend fun updateTarefa(tarefa: Tarefa)

    @Query("SELECT * FROM tarefas ORDER BY id DESC")
    fun readAllTarefas() : LiveData<List<Tarefa>>

    @Delete
    suspend fun deleteTarefa(tarefa: Tarefa)
}
