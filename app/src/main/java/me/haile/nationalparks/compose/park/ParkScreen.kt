package me.haile.nationalparks.compose.park

import android.text.Html
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import me.haile.nationalparks.viewmodel.ParkViewModel

data class ParkScreenCallbacks(
    val onFabClick: () -> Unit,
    val onBackClick: () -> Unit
)

@Composable
fun HtmlText(htmlString: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            text = Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT)
        }
    })
}

@Composable
fun ParkScreen(
    viewModel: ParkViewModel = hiltViewModel(),
    onFabClick: () -> Unit,
    onBackClick: () -> Unit,
    onGoToGalleryClick: () -> Unit
) {
    val articleText = viewModel.park.observeAsState()
    var isLongPressed by remember { mutableStateOf(false) }  // State to track if it's long pressed
    var selection by remember { mutableStateOf<TextRange?>(null) }
    val showCustomAction = selection != null
    //var showDialog by remember { mutableStateOf(false) }  // State to control dialog visibility

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = viewModel.park.value?.fullName ?: "",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
//        Text(
//            text = articleText.value ?: "",
//            modifier = Modifier.pointerInput(Unit) {
//                detectTapGestures(
//                    onLongPress = {
//                        //showDialog = true  // Show dialog when long pressed
//                        Logging.log("long pressed text detect $it")
//                    }
//                    , onDoubleTap = {
//                        Logging.log("double tapped detect: $it")
//                    },
//                    onPress = {
//                        Logging.log("on pressed: $it")
//                    }
//                )
//            }
//        )
//        BasicText(
//            text = articleText.value ?: "",
//            modifier = Modifier
//                .pointerInput(Unit) {
//                    detectTapGestures(
//                        onLongPress = {
//                            isLongPressed = !isLongPressed  // Toggle the long press state
//                        }
//                    )
//                }
//                .background(if (isLongPressed) Color.LightGray else Color.Transparent)  // Change background color on long press
//        )
        //  HtmlText(htmlString = articleText.value ?: "")

//        articleText.value?.let {
//            val spannableString = SpannableStringBuilder(articleText.value).toString()
//            val spanned = HtmlCompat.fromHtml(spannableString, HtmlCompat.FROM_HTML_MODE_COMPACT)
//            Text(text = spanned.toAnnotatedString())
//        }

        //Text(text = articleText.value ?: "")

//        SelectionContainer {
//            HtmlText(htmlString = articleText.value ?: "")
////            Text(text = articleText.value ?: "", onTextLayout = { textLayoutResult ->
////                selection = textLayoutResult.getWordBoundary(0)
////            })
//        }

        // Your custom action UI, shown when text is selected
        if (showCustomAction) {
            Button(onClick = { /* Perform your custom action */ }) {
                Text("Perform Custom Action")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onGoToGalleryClick() }) {
            Text(text = "Go to Gallery Screen")
        }
    }
}