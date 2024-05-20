package com.example.trabpratico.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "tarefas")
class Tarefa(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "tarefa") val tarefa: String,
    @ColumnInfo(name = "date") val date: String
) : Parcelable
