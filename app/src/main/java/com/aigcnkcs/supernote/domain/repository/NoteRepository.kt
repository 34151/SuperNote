package com.aigcnkcs.supernote.domain.repository

import com.aigcnkcs.supernote.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<List<NoteEntity>>
    fun getAllDeletedNotes(): Flow<List<NoteEntity>>
    fun getNotesByFolderId(folderId: Long?): Flow<List<NoteEntity>>
    fun getNotesByKeyWord(keyWord: String): Flow<List<NoteEntity>>

    suspend fun getNoteById(id: Long): NoteEntity?

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNote(noteEntity: NoteEntity)

    suspend fun deleteNotesByFolderId(folderId: Long?)

    suspend fun updateNote(note: NoteEntity)

}