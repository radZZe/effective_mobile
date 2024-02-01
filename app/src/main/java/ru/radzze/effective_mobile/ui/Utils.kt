package ru.radzze.effective_mobile.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.substring
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.radzze.effective_mobile.R
import ru.radzze.effective_mobile.ui.theme.sf_pro
import java.text.Normalizer.Form

@Composable
fun topAppBar(
    modifier: Modifier = Modifier,
    isNeedBackNavigate: Boolean = false,
    title: String,
    actionIcon: Int? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 16.dp),
    ) {
        if (isNeedBackNavigate) {
            Image(
                painterResource(id = R.drawable.back_icon),
                contentDescription = null,
                modifier = Modifier.align(
                    Alignment.CenterStart
                )
            )
        }
        Text(
            fontSize = 16.sp,
            color = Color.Black,
            fontFamily = sf_pro,
            text = title,
            modifier = Modifier.align(
                Alignment.Center
            ),
            fontWeight = FontWeight.Medium
        )
        if (actionIcon != null) {
            Image(
                painterResource(id = actionIcon),
                contentDescription = null,
                modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }

    }
}

@Preview
@Composable
fun previewTopAppBar() {
    topAppBar(isNeedBackNavigate = true, title = "Вход")
}


