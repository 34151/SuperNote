package com.aigcnkcs.supernote.presentation.event

import com.aigcnkcs.supernote.data.local.entity.NoteEntity
import com.aigcnkcs.supernote.domain.usecase.NoteOrder
import com.aigcnkcs.supernote.domain.usecase.OrderType
import kotlinx.collections.immutable.ImmutableList

sealed interface ListEvent {

    data class OpenNote(val noteEntity: NoteEntity) : ListEvent
    data class Search(val key: String) : ListEvent

    data class Sort(
        val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
        val filterFolder: Boolean = false,
        val folderId: Long? = null,
        val trash: Boolean = false
    ) : ListEvent

    data class DeleteNotes(val noteEntities: ImmutableList<NoteEntity>, val recycle: Boolean) : ListEvent
    data class MoveNotes(val noteEntities: ImmutableList<NoteEntity>, val folderId: Long?) : ListEvent
    data class RestoreNotes(val noteEntities: ImmutableList<NoteEntity>) : ListEvent

    data object ToggleOrderSection : ListEvent
    data object AddNote : ListEvent
}