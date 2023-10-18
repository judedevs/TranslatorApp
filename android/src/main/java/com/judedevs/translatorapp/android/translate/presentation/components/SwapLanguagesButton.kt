package com.judedevs.translatorapp.android.translate.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.judedevs.translatorapp.android.R
import com.judedevs.translatorapp.android.TranslatorTheme

@Composable
fun SwapLanguagesButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.swap),
            contentDescription = stringResource(id = R.string.swap_languages),
            tint = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
fun SwapLanguagesButtonPreview() {
    SwapLanguagesButton(onClick = { })
}