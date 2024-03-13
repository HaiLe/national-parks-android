package me.haile.nationalparks.compose.plan

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.haile.nationalparks.R
import me.haile.nationalparks.compose.common.HeaderText
import me.haile.nationalparks.compose.common.Separator
import me.haile.nationalparks.data.UnsplashPhoto
import me.haile.nationalparks.viewmodel.GalleryViewModel

@Composable
fun PlanScreen(galleryViewModel: GalleryViewModel = hiltViewModel()) {
    PlanScreenContent()
}

@Composable
private fun PlanScreenContent(
    onUpClick: () -> Unit = {},
) {
    Separator()
    HeaderText(text = stringResource(id = R.string.plan_a_trip_title))
}
