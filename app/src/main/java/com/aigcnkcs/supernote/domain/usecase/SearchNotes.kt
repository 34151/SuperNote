package com.aigcnkcs.supernote.domain.usecase

import com.aigcnkcs.supernote.data.local.entity.NoteEntity
import com.aigcnkcs.supernote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class SearchNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(keyWord: String): Flow<List<NoteEntity>> {
        return repository.getNotesByKeyWord(keyWord)
    }
}