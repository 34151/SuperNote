package com.aigcnkcs.supernote.presentation.state

data class DataActionState(
    val loading: Boolean = false,
    val progress: Float = 0f,
    val error: String = ""
)