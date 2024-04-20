package com.aigcnkcs.supernote.presentation.state

import androidx.compose.runtime.Stable
import com.aigcnkcs.supernote.data.local.entity.NoteEntity
import com.aigcnkcs.supernote.domain.usecase.NoteOrder
import com.aigcnkcs.supernote.domain.usecase.OrderType

@Stable
data class DataState(
    val notes: List<NoteEntity> = emptyList(),
    val filterTrash: Boolean = false,
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val filterFolder: Boolean = false,
    val folderId: Long? = null,
)