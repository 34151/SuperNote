package com.aigcnkcs.supernote.presentation.event

sealed interface NoteEvent {
    data class FolderChanged(val value: Long?) : NoteEvent
    data class Edit(val value: String) : NoteEvent
    data object SwitchType : NoteEvent
    data object Delete : NoteEvent
    data object Save : NoteEvent
}