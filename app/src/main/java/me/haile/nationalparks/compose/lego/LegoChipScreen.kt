import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.haile.nationalparks.utils.Logging

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegoChipScreen() {

    // Sample of chip jetpack compose ui component

    // The Chip component is a compact, interactive UI element.
    // It represents complex entities like a contact or tag, often with an icon and label.
    // It can be checkable, dismissible, or clickable.

    // https://developer.android.com/develop/ui/compose/components/chips

    Column(modifier = Modifier.padding(16.dp)) {
        AssistChip(onClick = { Log.d("Assist chip", "hello world") },
            label = { Text("Assist chip") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Localized description",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            })

        var selected by remember { mutableStateOf(false) }

        FilterChip(
            onClick = { selected = !selected },
            label = {
                Text("Filter chip")
            },
            selected = selected,
            leadingIcon = if (selected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )

        var enabled by remember { mutableStateOf(true) }
        if (!enabled) return

        InputChip(
            onClick = {
                onDismiss()
                enabled = !enabled
            },
            label = { Text("Input Chip") },
            selected = enabled,
            avatar = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            },
            trailingIcon = {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            },
        )
    }

    SuggestionChip(
        onClick = { Log.d("Suggestion chip", "hello world") },
        label = { Text("Suggestion chip") }
    )
}

fun onDismiss() {
    TODO("Not yet implemented")
}

@Preview
@Composable
fun LegoChipScreenPreview() {
    LegoChipScreen()
}

fun onChipClicked() {
    Logging.log("Button clicked")
}
