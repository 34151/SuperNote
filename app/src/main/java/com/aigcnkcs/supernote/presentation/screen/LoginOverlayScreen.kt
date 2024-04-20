package com.aigcnkcs.supernote.presentation.screen

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import com.aigcnkcs.supernote.R
import com.aigcnkcs.supernote.presentation.theme.Blue
import com.aigcnkcs.supernote.presentation.theme.Cyan
import com.aigcnkcs.supernote.presentation.theme.Green
import com.aigcnkcs.supernote.presentation.theme.Orange
import com.aigcnkcs.supernote.presentation.theme.Purple
import com.aigcnkcs.supernote.presentation.theme.Red
import com.aigcnkcs.supernote.presentation.theme.Yellow
import com.aigcnkcs.supernote.presentation.util.BiometricPromptManager

@Composable
fun LoginOverlayScreen(
    promptManager: BiometricPromptManager
) {

    // 判断系统版本是否大于android 12
    val modifier: Modifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        Modifier
    } else {
        Modifier.background(MaterialTheme.colorScheme.surface)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {}
            .then(modifier),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge.copy(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Red,
                        Orange,
                        Yellow,
                        Green,
                        Cyan,
                        Blue,
                        Purple
                    )
                )
            )
        )

        val title = stringResource(R.string.biometric_login)
        val subtitle = stringResource(R.string.log_in_using_your_biometric_credential)
        val negativeButtonText = stringResource(android.R.string.cancel)

        OutlinedButton(onClick = {
            promptManager.showBiometricPrompt(
                title = title,
                subtitle = subtitle,
                negativeButtonText = negativeButtonText
            )
        }) {
            Text(text = stringResource(R.string.login))
        }
    }
}