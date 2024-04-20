package com.aigcnkcs.supernote.presentation.event

sealed interface UiEvent {
    data object NavigateBack : UiEvent
}