package me.haile.nationalparks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.haile.nationalparks.compose.NationalParksApp
import me.haile.nationalparks.ui.NationalParksTheme

@AndroidEntryPoint
class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NationalParksTheme {
                NationalParksApp()
            }
        }
    }
}
