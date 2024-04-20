package com.aigcnkcs.supernote.data.di

import android.content.Context
import androidx.room.Room
import com.aigcnkcs.supernote.data.local.Database
import com.aigcnkcs.supernote.data.repository.DataStoreRepositoryImpl
import com.aigcnkcs.supernote.data.repository.FolderRepositoryImpl
import com.aigcnkcs.supernote.data.repository.NoteRepositoryImpl
import com.aigcnkcs.supernote.domain.usecase.AddFolder
import com.aigcnkcs.supernote.domain.repository.NoteRepository
import com.aigcnkcs.supernote.domain.usecase.AddNote
import com.aigcnkcs.supernote.domain.usecase.DeleteFolder
import com.aigcnkcs.supernote.domain.usecase.DeleteNote
import com.aigcnkcs.supernote.domain.usecase.DeleteNotesByFolderId
import com.aigcnkcs.supernote.domain.usecase.GetFolders
import com.aigcnkcs.supernote.domain.usecase.GetNotes
import com.aigcnkcs.supernote.domain.usecase.Operations
import com.aigcnkcs.supernote.domain.usecase.SearchNotes
import com.aigcnkcs.supernote.domain.usecase.UpdateFolder
import com.aigcnkcs.supernote.domain.usecase.UpdateNote
import com.aigcnkcs.supernote.domain.repository.DataStoreRepository
import com.aigcnkcs.supernote.domain.repository.FolderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(
            context,
            Database::class.java,
            Database.NAME
        ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(database: Database): NoteRepository =
        NoteRepositoryImpl(dao = database.noteDao)

    @Provides
    @Singleton
    fun provideFolderRepository(database: Database): FolderRepository =
        FolderRepositoryImpl(dao = database.folderDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(
        noteRepository: NoteRepository,
        folderRepository: FolderRepository
    ): Operations {
        return Operations(
            getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            searchNotes = SearchNotes(noteRepository),
            updateNote = UpdateNote(noteRepository),
            deleteNotesByFolderId = DeleteNotesByFolderId(noteRepository),
            addFolder = AddFolder(folderRepository),
            updateFolder = UpdateFolder(folderRepository),
            deleteFolder = DeleteFolder(folderRepository),
            getFolders = GetFolders(folderRepository)
        )
    }
}