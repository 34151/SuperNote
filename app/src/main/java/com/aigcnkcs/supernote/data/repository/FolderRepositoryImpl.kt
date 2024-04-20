package com.aigcnkcs.supernote.data.repository

import com.aigcnkcs.supernote.data.local.dao.FolderDao
import com.aigcnkcs.supernote.data.local.entity.FolderEntity
import com.aigcnkcs.supernote.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class FolderRepositoryImpl(
    private val dao: FolderDao
) : FolderRepository {

    override fun getAllFolders(): Flow<List<FolderEntity>> {
        return dao.getAllFolders()
    }

    override suspend fun insertFolder(folderEntity: FolderEntity) {
        dao.insertFolder(folderEntity)
    }

    override suspend fun deleteFolder(folderEntity: FolderEntity) {
        dao.deleteFolder(folderEntity)
    }

    override suspend fun updateFolder(folderEntity: FolderEntity) {
        dao.updateFolder(folderEntity)
    }

}