package me.haile.nationalparks.compose.park

import android.text.Html
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import me.haile.nationalparks.compose.common.AddressList
import me.haile.nationalparks.compose.common.HeaderText
import me.haile.nationalparks.compose.common.HorizontalImageLibrary
import me.haile.nationalparks.compose.common.MapViewComposable
import me.haile.nationalparks.compose.common.Separator
import me.haile.nationalparks.compose.common.StandardText
import me.haile.nationalparks.data.Activity
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.data.Topic
import me.haile.nationalparks.utils.CommonUtils
import me.haile.nationalparks.viewmodel.ParkViewModel

data class ParkScreenCallbacks(
    val onFabClick: () -> Unit, val onBackClick: () -> Unit
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
fun DisplayParkDetails(park: Park) {
    HeaderText(park.name)
    AddressList(addresses = park.addresses)
    Separator()
    MapViewComposable(park.latitude.toDouble(), park.longitude.toDouble())
    Separator()
    StandardText(text = park.url)
    Separator()
    HeaderText(text = "Hours")
    StandardText(text = CommonUtils.displayOperationHours(park.operatingHours))
    HeaderText(text = "About")
    StandardText(text = park.description)
    Separator()
    StandardText(text = park.directionsInfo)
    Separator()
    StandardText(text = park.directionsUrl)
    Separator()
    StandardText(text = park.states)
}

@Composable
fun DisplayActivities(activities: List<Activity>) {
    Text(text = "Activities:")
    LazyColumn {
        items(activities.size) { index ->
            Text(text = activities[index].name)
        }
    }
}

@Composable
fun DisplayTopics(topics: List<Topic>) {
    Text(text = "Topics:")
    LazyColumn {
        items(topics.size) { index ->
            Text(text = topics[index].name)
        }
    }
}

@Composable
fun ParkScreen(
    viewModel: ParkViewModel = hiltViewModel(),
    onFabClick: () -> Unit,
    onBackClick: () -> Unit,
    onGoToGalleryClick: (galleryQuery: String) -> Unit,
    onGoToThingsToDoClick: (parkCode: String) -> Unit,
    onPlanPhotographyTripClick: (parkCode: String) -> Unit
) {
    viewModel.fetchPark()
    val park = viewModel.park.observeAsState()
    val scrollState = rememberScrollState()
    park.value?.let {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            DisplayParkDetails(park = it)
            HeaderText(text = "Gallery")
            Separator()
            if (it.images.isNotEmpty()) {
                HorizontalImageLibrary(imageItems = it.images)
            }
            Separator()
            Button(onClick = { onGoToGalleryClick(it.fullName) }) {
                Text(text = "Go to Gallery Screen")
            }
            Separator()
            Button(onClick = { onGoToThingsToDoClick(it.parkCode) }) {
                Text(text = "Go to Things To Do screen")
            }

            Separator()
            Button(onClick = { onPlanPhotographyTripClick(it.parkCode) }) {
                Text(text = "Plan Photography Trip")
            }
        }

//        Spacer(modifier = Modifier.height(20.dp))
//        DisplayActivities(activities = it.activities)
//        Spacer(modifier = Modifier.height(20.dp))
//        DisplayTopics(topics = it.topics)
    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
////        Text(
////            text = park.value?.fullName ?: "",
////            style = MaterialTheme.typography.headlineLarge
////        )
//
//
////        Text(
////            text = articleText.value ?: "",
////            modifier = Modifier.pointerInput(Unit) {
////                detectTapGestures(
////                    onLongPress = {
////                        //showDialog = true  // Show dialog when long pressed
////                        Logging.log("long pressed text detect $it")
////                    }
////                    , onDoubleTap = {
////                        Logging.log("double tapped detect: $it")
////                    },
////                    onPress = {
////                        Logging.log("on pressed: $it")
////                    }
////                )
////            }
////        )
////        BasicText(
////            text = articleText.value ?: "",
////            modifier = Modifier
////                .pointerInput(Unit) {
////                    detectTapGestures(
////                        onLongPress = {
////                            isLongPressed = !isLongPressed  // Toggle the long press state
////                        }
////                    )
////                }
////                .background(if (isLongPressed) Color.LightGray else Color.Transparent)  // Change background color on long press
////        )
//        //  HtmlText(htmlString = articleText.value ?: "")
//
////        articleText.value?.let {
////            val spannableString = SpannableStringBuilder(articleText.value).toString()
////            val spanned = HtmlCompat.fromHtml(spannableString, HtmlCompat.FROM_HTML_MODE_COMPACT)
////            Text(text = spanned.toAnnotatedString())
////        }
//
//        //Text(text = articleText.value ?: "")
//
////        SelectionContainer {
////            HtmlText(htmlString = articleText.value ?: "")
//////            Text(text = articleText.value ?: "", onTextLayout = { textLayoutResult ->
//////                selection = textLayoutResult.getWordBoundary(0)
//////            })
////        }
//
//        // Your custom action UI, shown when text is selected
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = { onGoToGalleryClick() }) {
//            Text(text = "Go to Gallery Screen")
//        }
}
