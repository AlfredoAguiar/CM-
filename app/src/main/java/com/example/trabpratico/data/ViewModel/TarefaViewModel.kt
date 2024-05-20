package com.example.trabpratico.data.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
//import com.example.trabpratico.data.RetrofitClient
import com.example.trabpratico.data.entities.Tarefa
import com.example.trabpratico.data.repository.TarefasRepository
import com.example.trabpratico.data.db.TarefaDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TarefaViewModel(application: Application) : AndroidViewModel(application){
    val readAllTarefas: LiveData<List<Tarefa>>
    private val repository: TarefasRepository

    init {
        val tarefaDao = TarefaDatabase.getDatabase(application).tarefaDao()
        //val apiService = RetrofitClient.apiService
        repository = TarefasRepository(tarefaDao)//adicionar apiService de entrada
        readAllTarefas = repository.readAllTarefas
    }

    fun  addTarefa(tarefa: Tarefa){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTarefa(tarefa)
        }
    }

    fun updateTarefa(tarefa: Tarefa) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTarefa(tarefa)
        }
    }

    fun  deleteTarefa(tarefa: Tarefa) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTarefa(tarefa)
        }
    }

    fun gerTarefasFromApi() {
        viewModelScope.launch {
            try {
                val tasks = repository.getTasksFromApi()
                // Handle tasks fetched from API
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
