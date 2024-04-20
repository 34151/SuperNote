package com.aigcnkcs.supernote.domain.usecase

import com.aigcnkcs.supernote.data.local.entity.NoteEntity
import com.aigcnkcs.supernote.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: NoteEntity) {
        repository.insertNote(note)
    }
}