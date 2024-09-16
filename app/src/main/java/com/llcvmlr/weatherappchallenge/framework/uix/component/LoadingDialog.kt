package com.llcvmlr.weatherappchallenge.framework.uix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.llcvmlr.weatherappchallenge.ui.theme.WeatherAppChallenegTheme

/**
 * A composable that displays a loading dialog with an optional title and message.
 *
 * @param showDialog Boolean flag to control the visibility of the dialog.
 * @param onDismissRequest Callback invoked when the dialog is dismissed.
 * @param title Optional title to display at the top of the dialog.
 * @param showTitle Boolean flag to control whether the title is shown. Default is true.
 * @param message Optional message to display below the title.
 */
@Composable
fun LoadingDialog(
    showDialog: Boolean = false,
    onDismissRequest: () -> Unit = {},
    title: String? = null,
    showTitle: Boolean = true,
    message: String? = null
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismissRequest) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = MaterialTheme.shapes.medium)
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if(showTitle) {
                        title?.let {
                            Text(
                                text = it, style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.surfaceTint
                            )
                        }
                    }

                    message?.let {
                        Text(
                            text = it, style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                }
                CircularProgressIndicator(
                    color =  MaterialTheme.colorScheme.surfaceVariant,
                    strokeWidth= 2.dp,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}


/**
 * Preview of the [LoadingDialog] composable with title and message visible.
 */
@Preview
@Composable
fun PreviewLoadingDialog() {
    WeatherAppChallenegTheme {
        LoadingDialog(
            showDialog = true,
            onDismissRequest = {  },
            title = "Loading",
            message = "Please wait..."
        )
    }
}

/**
 * Preview of the [LoadingDialog] composable with title hidden.
 */
@Preview
@Composable
fun PreviewLoadingDialogHideTitle() {
    WeatherAppChallenegTheme {
        LoadingDialog(
            showDialog = true,
            onDismissRequest = {  },
            title = "Loading",
            showTitle = false,
            message = "Please wait..."
        )
    }
}
