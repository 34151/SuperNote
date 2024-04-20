package com.aigcnkcs.supernote.domain.usecase

import com.aigcnkcs.supernote.data.local.entity.FolderEntity
import com.aigcnkcs.supernote.domain.repository.FolderRepository

class UpdateFolder(
    private val repository: FolderRepository
) {

    suspend operator fun invoke(folderEntity: FolderEntity) {
        repository.updateFolder(folderEntity)
    }
}