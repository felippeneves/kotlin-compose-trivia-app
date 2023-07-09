package com.felippeneves.jettrivia.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "TB_ANSWERS")
data class Answer(
    @PrimaryKey
    @ColumnInfo(name = "ANSWER_ID")
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "ANSWER_INDEX_QUESTION")
    val indexQuestion: Int,

    @ColumnInfo(name = "ANSWER_CHOICE")
    val choice: String,
)
