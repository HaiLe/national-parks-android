package me.haile.nationalparks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.haile.nationalparks.compose.NationalParksApp

@AndroidEntryPoint
class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NationalParksApp()
        }
    }
}
