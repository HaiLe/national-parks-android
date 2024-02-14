package me.haile.nationalparks.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import me.haile.nationalparks.R


@RequiresApi(Build.VERSION_CODES.Q)
val CustomFontFamily = FontFamily(
    Font(R.font.opensans_regular, FontWeight.Normal),
    Font(R.font.opensans_bold, FontWeight.Bold)
)