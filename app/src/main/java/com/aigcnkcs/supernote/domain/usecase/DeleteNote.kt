package com.aigcnkcs.supernote.domain.usecase

import com.aigcnkcs.supernote.data.local.entity.NoteEntity
import com.aigcnkcs.supernote.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(noteEntity: NoteEntity) {
        repository.deleteNote(noteEntity)
    }
}