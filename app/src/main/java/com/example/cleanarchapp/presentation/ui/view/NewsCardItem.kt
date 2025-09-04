package com.example.cleanarchapp.presentation.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.cleanarchapp.domain.model.Article

@Composable
fun CardItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
        ) {
            val (txtTitle, txtDescription, txtPublishedAt, txtAuthor, imgNewsImage) = createRefs()
            Text(
                modifier = Modifier.constrainAs(ref = txtTitle) {
                    top.linkTo(anchor = parent.top)
                    start.linkTo(anchor = parent.start)
                },
                text = article.title ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .constrainAs(ref = txtDescription) {
                        top.linkTo(txtTitle.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                        end.linkTo(imgNewsImage.start, margin = 8.dp)
                        width = fillToConstraints
                    },
                text = article.description ?: "",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.constrainAs(ref = txtPublishedAt) {
                    top.linkTo(anchor = txtDescription.bottom)
                    start.linkTo(anchor = parent.start)
                }.padding(top = 5.dp),
                text = article.publishedAt ?: ""
            )
            Text(
                modifier = Modifier.constrainAs(ref = txtAuthor) {
                    top.linkTo(anchor = txtPublishedAt.bottom)
                }.padding(top = 5.dp),
                text = article.author ?: "",
                fontSize = 18.sp
            )
            AsyncImage(
                modifier = Modifier.constrainAs(imgNewsImage) {
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    end.linkTo(anchor = parent.end)
                }.width(110.dp).height(110.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.imageUrl)
                    .build(),
                contentDescription = "News image"

            )

        }
    }
}