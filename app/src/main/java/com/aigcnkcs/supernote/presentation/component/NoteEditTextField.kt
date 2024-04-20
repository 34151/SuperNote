package com.aigcnkcs.supernote.presentation.component

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.content.MediaType
import androidx.compose.foundation.content.consume
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.content.hasMediaType
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import com.aigcnkcs.supernote.R
import com.aigcnkcs.supernote.presentation.util.add
import com.aigcnkcs.supernote.presentation.util.bold
import com.aigcnkcs.supernote.presentation.util.inlineCode
import com.aigcnkcs.supernote.presentation.util.inlineFunction
import com.aigcnkcs.supernote.presentation.util.italic
import com.aigcnkcs.supernote.presentation.util.mark
import com.aigcnkcs.supernote.presentation.util.quote
import com.aigcnkcs.supernote.presentation.util.strikeThrough
import com.aigcnkcs.supernote.presentation.util.underline

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteEditTextField(
    modifier: Modifier,
    state: TextFieldState,
    readMode: Boolean,
    onFocusChanged: (Boolean) -> Unit
) = BasicTextField(
    // The contentReceiver modifier is used to receive text content from the clipboard or drag-and-drop operations.
    modifier = modifier
        .contentReceiver { transferableContent ->
            if (transferableContent.hasMediaType(MediaType.Text)) {
                transferableContent.consume { item: ClipData.Item ->
                    val hasText = item.text.isNotEmpty()
                    if (hasText) {
                        state.edit { add(item.text.toString()) }
                    }
                    hasText
                }
            }
            null
        }
        .onFocusChanged {
            onFocusChanged(it.isFocused)
        }
        .onPreviewKeyEvent { keyEvent ->
            if (keyEvent.type == KeyEventType.KeyDown) {
                if (keyEvent.isCtrlPressed) {
                    if (keyEvent.isShiftPressed) {
                        when (keyEvent.key) {
                            Key.K -> {
                                state.edit { inlineCode() }
                                true
                            }

                            Key.M -> {
                                state.edit { inlineFunction() }
                                true
                            }

                            Key.Q -> {
                                state.edit { quote() }
                                true
                            }

                            else -> false
                        }
                    } else {
                        when (keyEvent.key) {
                            Key.B -> {
                                state.edit { bold() }
                                true
                            }

                            Key.D -> {
                                state.edit { strikeThrough() }
                                true
                            }

                            Key.I -> {
                                state.edit { italic() }
                                true
                            }

                            Key.M -> {
                                state.edit { mark() }
                                true
                            }

                            Key.U -> {
                                state.edit { underline() }
                                true
                            }

                            else -> false
                        }
                    }

                } else {
                    false
                }
            } else {
                false
            }
        },
    readOnly = readMode,
    state = state,
    textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
    decorator = { innerTextField ->
        Box {
            if (state.text.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.content),
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
            innerTextField()
        }
    }
)
