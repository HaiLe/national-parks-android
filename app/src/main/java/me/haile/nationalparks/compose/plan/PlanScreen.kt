package me.haile.nationalparks.compose.plan

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import me.haile.nationalparks.R
import me.haile.nationalparks.compose.common.HeaderText
import me.haile.nationalparks.compose.common.Separator
import me.haile.nationalparks.compose.common.StandardText
import me.haile.nationalparks.data.openai.Choice
import me.haile.nationalparks.viewmodel.PlanViewModel

@Composable
fun PlanScreen(planViewModel: PlanViewModel = hiltViewModel()) {
    planViewModel.fetchPhotographyTrip()
    val choice = planViewModel.choice.observeAsState()
    choice.value?.let {
        LazyColumn {
            item {
                Separator()
                HeaderText(text = stringResource(id = R.string.plan_a_trip_title))
            }

            item {
                Separator()
                StandardText(text = it.message.content)
            }
        }
    }
}

@Composable
private fun PlanScreenContent(
    choice: LiveData<Choice>,
    onUpClick: () -> Unit = {},
) {

}
