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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
//    homeViewModel: HomeViewModel = hiltViewModel(),
//    onArticleClick: (NewsArticle) -> Unit = {}
) {
    //val myArticleList = homeViewModel.articles.observeAsState()
    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) },
        bottomBar = { BottomNavigationBar() }) { innerPadding ->
        HelloContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun HelloContent(modifier: Modifier = Modifier) {

    val listItems = listOf(
        "Hello US National Park",
        "Hello US National Park 2",
        "Hello US National Park 3",
        "Hello US National Park 4",
        "Hello US National Park 5"
    )
    LazyColumn(modifier = modifier) {
        items(listItems.size) { index ->
            val article = listItems[index]
            ListItem(
                newsArticle = article,
            )
        }
    }
}

@Composable
fun ListItem(newsArticle: String) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
        }) {
        Text(text = newsArticle, fontWeight = FontWeight.Bold)
    }
}


//@Composable
//fun BodyContent(
//    articles: List<NewsArticle>,
//    modifier: Modifier = Modifier,
//    onArticleClick: (NewsArticle) -> Unit
//) {
//    //val itemList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6") // Sample list
//    LazyColumn(modifier = modifier) {
//        items(articles.size) { index ->
//            val article = articles[index]
//            ListItem(
//                newsArticle = articles[index],
//                onArticleClick = onArticleClick
//            )
//        }
//    }
//}

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
