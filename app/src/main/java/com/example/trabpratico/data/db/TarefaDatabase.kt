package com.example.trabpratico.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trabpratico.data.dao.TarefaDao
import com.example.trabpratico.data.entities.Tarefa

@Database(entities = [Tarefa :: class], version = 1, exportSchema = false)
abstract class TarefaDatabase : RoomDatabase(){
    abstract  fun  tarefaDao(): TarefaDao

    companion object {
        @Volatile
        private var INSTANCE: TarefaDatabase? = null

        fun getDatabase(context: Context): TarefaDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TarefaDatabase::class.java,
                    "tarefa_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}