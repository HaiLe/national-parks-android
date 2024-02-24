package me.haile.nationalparks.compose.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import me.haile.nationalparks.compose.common.HeaderText
import me.haile.nationalparks.compose.common.Separator
import me.haile.nationalparks.compose.common.StandardText
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.data.db.ParkEntity
import me.haile.nationalparks.ui.NationalParksTheme
import me.haile.nationalparks.utils.CommonUtils
import me.haile.nationalparks.viewmodel.FavoritesViewModel
import me.haile.nationalparks.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel = hiltViewModel(), onParkClick: (ParkEntity) -> Unit = {}
) {
    favoritesViewModel.getFavoriteParks()
    favoritesViewModel.favoriteParksLiveData.observeAsState().value?.let {
        BodyContent(
            parks = it,
            modifier = Modifier.padding(16.dp),
            onParkClick,
            favoritesViewModel
        )
    }
}


@Composable
fun ListItem(
    park: ParkEntity, onParkClick: (ParkEntity) -> Unit, parksViewModel: FavoritesViewModel
) {
    LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            // add to favorites
            // parksViewModel.addParkToFavoriteList(park)
            onParkClick(park)
        }) {
        HeaderText(text = park.name)
        StandardText(text = CommonUtils.getListOfStateNames(park.states).toString())
        Separator()
        StandardText(text = park.description)
        Separator()
    }
}

@Composable
fun BodyContent(
    parks: List<ParkEntity>,
    modifier: Modifier = Modifier,
    onParkClick: (ParkEntity) -> Unit,
    favoritesViewModel: FavoritesViewModel
) {
    LazyColumn(modifier = modifier) {
        items(parks.size) { index ->
            ListItem(
                park = parks[index] ?: return@items, onParkClick = onParkClick, favoritesViewModel
            )
        }
    }
    //HeaderText(text = "Favorites")
}

@Composable
fun BodyContent1(
    parksLiveData: LiveData<List<ParkEntity>>,
    modifier: Modifier = Modifier,
    onParkClick: (Park) -> Unit
) {
    val parks = parksLiveData.value ?: emptyList()
    LazyColumn(modifier = modifier) {
        items(parks.size) { index ->
            StandardText(text = parks[index].name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NationalParksTheme {
        FavoritesScreen()
    }
}
