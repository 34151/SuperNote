package com.aigcnkcs.supernote.presentation.state

data class SettingsState(
    val theme: Int = -1,
    val color: Int = 0,
    val needPassword: Boolean = false
)
