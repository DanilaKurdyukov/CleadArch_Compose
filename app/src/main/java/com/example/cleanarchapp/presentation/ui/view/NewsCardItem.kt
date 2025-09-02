package com.example.cleanarchapp.presentation.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cleanarchapp.R

@Composable
fun CardItem(modifier: Modifier) {
    Card(
        modifier = modifier
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
            val horizontalGuideline = createGuidelineFromTop(offset = 30.dp)
            val verticalGuideline = createGuidelineFromEnd(offset = 30.dp)
            val (txtTitle, txtDescription, txtPublishedAt, txtAuthor, imgNewsImage) = createRefs()
            Text(
                modifier = Modifier.constrainAs(ref = txtTitle) {
                    top.linkTo(anchor = parent.top)
                    start.linkTo(anchor = parent.start)
                },
                text = "Title"
            )
            Text(
                modifier = Modifier.constrainAs(ref = txtDescription) {
                    top.linkTo(anchor = txtTitle.bottom)
                },
                text = "Description"
            )
            Text(
                modifier = Modifier.constrainAs(ref = txtPublishedAt) {
                    top.linkTo(anchor = txtDescription.bottom)
                    start.linkTo(anchor = parent.start)
                },
                text = "Mon, 01 Sep 2025"
            )
            Text(
                modifier = Modifier.constrainAs(ref = txtAuthor) {
                    top.linkTo(anchor = txtPublishedAt.bottom)
                },
                text = "Annie Kelly"
            )
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .constrainAs(ref = imgNewsImage) {
                        top.linkTo(anchor = horizontalGuideline)
                        bottom.linkTo(anchor = txtAuthor.top)
                        end.linkTo(anchor = verticalGuideline)
                    },
                contentDescription = "News image",
                imageVector = ImageVector.vectorResource(id = R.drawable.outline_emoji_people_24)
            )
        }
    }
}