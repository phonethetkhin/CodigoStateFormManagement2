package com.ptk.codigostateformmanagement.ui.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.ptk.codigostateformmanagement.ui.theme.Orange
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DailyVitaButton(text: String, modifier: Modifier = Modifier, onclick: () -> Unit) {
    Button(
        onClick = { onclick.invoke() },
        colors = ButtonDefaults.buttonColors(containerColor = Orange),
        modifier = modifier,
        shape = RoundedCornerShape(10.sdp)
    ) {
        Text(
            text,
            fontSize = 14.ssp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
