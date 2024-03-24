import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.haile.nationalparks.compose.common.HeaderText
import me.haile.nationalparks.compose.common.Separator
import me.haile.nationalparks.utils.Logging

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegoChipScreen() {
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
    }
}

@Preview
@Composable
fun LegoChipScreenPreview() {
    LegoChipScreen()
}

fun onChipClicked() {
//    Toast.makeText(
//        LocalContext.current,
//        "Button clicked",
//        Toast.LENGTH_SHORT
//    ).show()
    Logging.log("Button clicked")
}
