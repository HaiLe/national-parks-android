package me.haile.nationalparks.compose.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.viewmodel.ParksViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    parksViewModel: ParksViewModel = hiltViewModel(),
    onParkClick: (Park) -> Unit = {}
) {
    parksViewModel.loadParks()
    val parks = parksViewModel.parks.observeAsState()
    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) },
        bottomBar = { BottomNavigationBar() }) { innerPadding ->
        BodyContent(
            parks = parks.value ?: listOf(),
            modifier = Modifier.padding(innerPadding),
            onParkClick
        )
    }
}

//@Composable
//fun HelloContent(parks: List<Park>, modifier: Modifier = Modifier) {
//    LazyColumn(modifier = modifier) {
//        items(parks.size) { index ->
//            val park = parks[index]
//            ListItem(
//                park = park,
//            )
//        }
//    }
//}

@Composable
fun ListItem(
    park: Park,
    onParkClick: (Park) -> Unit
) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            onParkClick(park)
        }) {
        Text(text = park.fullName, fontWeight = FontWeight.Bold)
        Text(text = park.states, fontWeight = FontWeight.Bold)
        Text(text = park.description)
        Text(text = park.directionsInfo)
    }
}

@Composable
fun BodyContent(
    parks: List<Park>,
    modifier: Modifier = Modifier,
    onParkClick: (Park) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(parks.size) { index ->
            ListItem(
                park = parks[index],
                onParkClick = onParkClick
            )
        }
    }
}

//@Composable
//fun ListItem(newsArticle: NewsArticle, onArticleClick: ((NewsArticle) -> Unit)) {
//    val context = LocalContext.current
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .padding(16.dp)
//        .clickable {
//            val sharedPreferencesManager = SharedPreferencesManager(context = context)
//            // Save a string
//            sharedPreferencesManager.saveString(SharedPreferencesManager.CLICKED_LINK_KEY, newsArticle.url)
//
//            // Retrieve the string
//            onArticleClick(
//              newsArticle
//            )
//        }) {
//        Text(text = newsArticle.title, fontWeight = FontWeight.Bold)
//        Text(text = newsArticle.description)
//    }
//}

@Composable
fun BottomNavigationBar() {
    BottomAppBar {
//            BottomNavigation {
//                BottomNavigationItem(
//                    selected = true, // You can control the selected state as needed
//                    onClick = {}, // Add your logic for what happens when the item is clicked
//                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//                    label = { Text("Home") }
//                )
//                // Add more BottomNavigationItem here as needed
//            }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
