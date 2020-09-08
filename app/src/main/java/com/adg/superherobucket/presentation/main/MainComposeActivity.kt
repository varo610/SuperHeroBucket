package com.adg.superherobucket.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.adg.superherobucket.R


class MainComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LayoutMainActivity()
        }

    }

    @Composable
    fun LayoutMainActivity() {
        Scaffold(
            topBar = { Toolbar() }
        ) {
            BodyContent(Modifier.padding(it))
        }
    }

    @Composable
    fun BodyContent(modifier: Modifier = Modifier) {
        EmptyBody(modifier)
    }

    @Composable
    fun EmptyBody(modifier: Modifier = Modifier) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val (image, text) = createRefs()
            Image(
                asset = imageResource(id = androidx.compose.foundation.layout.R.drawable.header),
                modifier = Modifier
                    .constrainAs(image) { centerTo(parent) }
                    .preferredSize(200.dp, 200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "No Superheros",
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(anchor = image.bottom, margin = 8.dp)
                    centerHorizontallyTo(parent)
                }
            )
        }
    }

    @Composable
    fun Toolbar() {
        TopAppBar(title = { Text(text = stringResource(id = androidx.compose.foundation.layout.R.string.app_name)) })
    }

    @Preview
    @Composable
    fun MainActivityPreview() {
        MainActivity()
    }

}
