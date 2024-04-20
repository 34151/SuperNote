package com.aigcnkcs.supernote.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aigcnkcs.supernote.data.local.entity.NoteEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: NoteEntity,
    isEnabled: Boolean,
    isSelected: Boolean,
    onNoteClick: (NoteEntity) -> Unit,
    onEnableChange: (Boolean) -> Unit
) = ElevatedCard(modifier = modifier) {

    val title = note.title
    val content = note.content

    Box(modifier = Modifier
        .fillMaxWidth()
        .sizeIn(minHeight = 80.dp, maxHeight = 360.dp)
        .combinedClickable(
            onLongClick = {
                onEnableChange(true)
            },
            onClick = { onNoteClick(note) }
        )) {
        if (isEnabled)
            Checkbox(
                checked = isSelected, onCheckedChange = null, modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.TopEnd)
            )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {

            if (title.isNotEmpty())
                Text(
                    modifier = Modifier.basicMarquee(),
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )

            if (title.isNotEmpty() && content.isNotEmpty())
                Spacer(modifier = Modifier.height(8.dp))

            if (content.isNotEmpty())
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis
                )
        }
    }
}
