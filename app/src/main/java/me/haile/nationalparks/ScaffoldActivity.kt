package me.haile.nationalparks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import me.haile.nationalparks.compose.MemorApp

class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemorApp()
        }
    }
}
