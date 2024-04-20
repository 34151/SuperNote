package com.aigcnkcs.supernote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aigcnkcs.supernote.data.local.dao.FolderDao
import com.aigcnkcs.supernote.data.local.dao.NoteDao
import com.aigcnkcs.supernote.data.local.entity.FolderEntity
import com.aigcnkcs.supernote.data.local.entity.NoteEntity

@Database(
    version = 1,
    entities = [NoteEntity::class, FolderEntity::class]
)
abstract class Database : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val folderDao: FolderDao

    companion object {
        const val NAME = "NOTE_DB"
    }
}