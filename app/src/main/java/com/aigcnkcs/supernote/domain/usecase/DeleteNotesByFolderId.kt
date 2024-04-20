package com.aigcnkcs.supernote.domain.usecase

import com.aigcnkcs.supernote.domain.repository.NoteRepository

class DeleteNotesByFolderId(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(folderId: Long?) {
        repository.deleteNotesByFolderId(folderId)
    }
}