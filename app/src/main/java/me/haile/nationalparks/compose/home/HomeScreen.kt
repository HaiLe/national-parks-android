package me.haile.nationalparks.compose.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    parksViewModel: HomeViewModel = hiltViewModel(), onParkClick: (Park) -> Unit = {}
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) },
        bottomBar = { BottomNavigationBar() }) { innerPadding ->
        BodyContent(
            parks = parksViewModel.parksData, modifier = Modifier.padding(innerPadding), onParkClick
        )
    }
}

@Composable
fun ListItem(
    park: Park, onParkClick: (Park) -> Unit
) {
    LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            onParkClick(park)
        }) {
        Text(text = park.fullName, fontWeight = FontWeight.Bold)
        Text(text = park.states, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = park.description)
        Spacer(modifier = Modifier.height(16.dp))
//        Text(text = park.directionsInfo)
    }
}

@Composable
fun BodyContent(
    parks: Flow<PagingData<Park>>, modifier: Modifier = Modifier, onParkClick: (Park) -> Unit
) {
    val pagingItems: LazyPagingItems<Park> = parks.collectAsLazyPagingItems()
    LazyColumn(modifier = modifier) {
        items(pagingItems.itemCount) { index ->
            ListItem(
                park = pagingItems[index] ?: return@items, onParkClick = onParkClick
            )
        }
    }
}

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
