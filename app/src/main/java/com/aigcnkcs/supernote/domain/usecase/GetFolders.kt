package com.aigcnkcs.supernote.domain.usecase

import com.aigcnkcs.supernote.data.local.entity.FolderEntity
import com.aigcnkcs.supernote.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class GetFolders(
    private val repository: FolderRepository
) {

    operator fun invoke(): Flow<List<FolderEntity>> {
        return repository.getAllFolders()
    }
}