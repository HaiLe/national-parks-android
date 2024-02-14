package me.haile.nationalparks.compose.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.displaySmall
    )
}
@Composable
fun StandardText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyMedium
    )
}
@Composable
fun Separator() {
    Spacer(modifier = Modifier.height(20.dp))
}
