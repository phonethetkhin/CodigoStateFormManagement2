package com.ptk.codigostateformmanagement.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.ptk.codigostateformmanagement.R
import com.ptk.codigostateformmanagement.ui.composables.DailyVitaButton
import com.ptk.codigostateformmanagement.ui.theme.DarkBlue
import com.ptk.codigostateformmanagement.ui.theme.Green
import com.ptk.codigostateformmanagement.viewmodel.HomeViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun IntroductionScreen(homeViewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)

    ) {
        Column(
            Modifier
                .weight(1F)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Welcome to DailyVita",
                fontSize = 24.ssp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 32.sdp)
            )
            Text(
                text = "Hello, we are here to make your life healthier and happier",
                fontSize = 14.ssp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 16.sdp)
            )

            Image(
                painter = painterResource(id = R.drawable.conversation),
                contentDescription = "ConversationIcon",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.sdp)
                    .padding(top = 32.sdp, start = 8.sdp, end = 8.sdp)
            )

            Text(
                text = "We will ask couple of questions to better understand your vitamin need.",
                fontSize = 14.ssp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 32.sdp)
            )

            DailyVitaButton(
                text = "Get Started", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.sdp, horizontal = 16.sdp)
            ) {
                homeViewModel.toggleCurrentScreen(2)
            }
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp), progress = 0F, color = DarkBlue
        )
    }
}