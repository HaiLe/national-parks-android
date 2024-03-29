import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.haile.nationalparks.compose.common.HeaderText
import me.haile.nationalparks.compose.common.Separator
import me.haile.nationalparks.utils.Logging

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegoScreen(onShowCardButtonClicked: () -> Unit,
               onShowChipButtonClicked: () -> Unit) {

    val openAlertDialog = remember { mutableStateOf(false) }

    Column (modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        HeaderText(text = "Buttons")
        Button(onClick = { onShowCardButtonClicked() }) {
            Text("Show Card")
        }

        FilledTonalButton(onClick = { onShowChipButtonClicked() }) {
            Text("Show Chip")
        }

        OutlinedButton(onClick = {openAlertDialog.value = true}) {
            Text("Button")
        }

        ElevatedButton(onClick = { onButtonClicked() }) {
            Text("Button")
        }
        TextButton(onClick = {onButtonClicked()}) {
            Text("Button")
        }

        Separator()

        HeaderText(text = "Floating Action Buttons")

        FloatingActionButton(
            onClick = { onButtonClicked() },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, "Floating Action Button.")
        }

        Separator()

        SmallFloatingActionButton(
            onClick = { onButtonClicked() },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }

        Separator()

        LargeFloatingActionButton(
            onClick = { onButtonClicked() },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary,
            shape = CircleShape
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }

        Separator()

        ExtendedFloatingActionButton(
            onClick = { onButtonClicked() },
            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            text = { Text(text = "Extended FAB") },
        )

        showAlertDialog(openAlertDialog)
    }
}

fun onButtonClicked() {
//    Toast.makeText(
//        LocalContext.current,
//        "Button clicked",
//        Toast.LENGTH_SHORT
//    ).show()
    Logging.log("Button clicked")
}

@Composable
fun showAlertDialog(openAlertDialog: MutableState<Boolean>) {
    when {
        openAlertDialog.value -> {
            LegoDialog(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    println("Confirmation registered") // Add logic here to handle confirmation.
                },
                dialogTitle = "Alert dialog example",
                dialogText = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info
            )
        }
    }
}

