package me.haile.nationalparks.compose.common

import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.haile.nationalparks.data.ImageItem

@Composable
fun HorizontalImageLibrary(imageItems: List<ImageItem>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(imageItems.size) { item ->
            ImageCard(imageItems[item])
        }
    }
}

@Composable
fun ImageCard(imageItem: ImageItem) {
    Column(
        modifier = Modifier.width(200.dp)
    ) {
        GlideImage(url = imageItem.url, contentDescription = imageItem.altText)
        Text(text = imageItem.title, maxLines = 1, fontSize = 10.sp, modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun GlideImage(url: String, contentDescription: String?) {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            ImageView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                Glide.with(context)
                    .load(url)
                    .apply(RequestOptions().centerCrop())
                    .into(this)
            }
        },
        update = { imageView ->
            Glide.with(context)
                .load(url)
                .apply(RequestOptions().centerCrop())
                .into(imageView)
        },
        modifier = Modifier
            .size(150.dp)         // Set the size of the image
    )
}