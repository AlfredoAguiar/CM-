package com.example.trabpratico.data.repository

import androidx.lifecycle.LiveData
import com.example.trabpratico.data.api.RetrofitClient.apiService
//import com.example.trabpratico.data.RetrofitClient.apiService
import com.example.trabpratico.data.dao.TarefaDao
import com.example.trabpratico.data.entities.Tarefa

class TarefasRepository(private  val tarefaDao: TarefaDao) {
    val readAllTarefas : LiveData<List<Tarefa>> = tarefaDao.readAllTarefas()

    suspend fun addTarefa(tarefa: Tarefa){
        tarefaDao.addTarefa(tarefa)
    }

    suspend fun updateTarefa(tarefa: Tarefa) {
        tarefaDao.updateTarefa(tarefa)
    }

    suspend fun deleteTarefa(tarefa: Tarefa) {
        tarefaDao.deleteTarefa(tarefa)
    }

    suspend fun getTasksFromApi(): String {
        return apiService.getTeste()
    }
}
