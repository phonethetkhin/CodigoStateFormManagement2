@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.ptk.codigostateformmanagement.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.ptk.codigostateformmanagement.ui.composables.DailyVitaButton
import com.ptk.codigostateformmanagement.ui.theme.DarkBlue
import com.ptk.codigostateformmanagement.ui.theme.Green
import com.ptk.codigostateformmanagement.ui.theme.Grey
import com.ptk.codigostateformmanagement.ui.theme.Orange
import com.ptk.codigostateformmanagement.viewmodel.HomeViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun AllergyScreen(homeViewModel: HomeViewModel) {
    LaunchedEffect(key1 = "") {
        homeViewModel.loadAllergyData()
    }
    val uiStates by homeViewModel.uiStates.collectAsState()


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
                text = "Write any specific allergies or sensitivity towards specific things. (optional) ",

                fontSize = 16.ssp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 32.sdp)
            )
            Log.e("Allergy", uiStates.suggestions.toString())
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(7.sdp),
                modifier = Modifier
                    .padding(7.sdp)
            ) {
                uiStates.selectedAllergy.filter { it.checked }.forEach { allergy ->
                    val color = DarkBlue
                    val textColor = Color.White
                    Text(
                        modifier = Modifier
                            .padding(top = 8.sdp)
                            .background(color = color, shape = CircleShape)
                            .padding(vertical = 4.sdp, horizontal = 16.sdp),

                        text = allergy.name,
                        fontSize = 11.ssp,
                        color = textColor,

                        )
                }
            }
            OutlinedTextField(
                onValueChange = homeViewModel::toggleAllergyText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.sdp, start = 16.sdp, end = 16.sdp)
                    .background(Color.White),
                value = uiStates.allergyText,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.sdp)
                    .alpha(alpha = if (uiStates.suggestions.isNotEmpty()) 1F else 0F)
                    .padding(start = 16.sdp, end = 16.sdp)
                    .clip(RoundedCornerShape(bottomStart = 10.sdp, bottomEnd = 10.sdp))
                    .background(Grey),
            ) {
                items(uiStates.suggestions) {
                    Text("$it", modifier = Modifier
                        .padding(start = 8.sdp, top = 8.sdp)
                        .clickable {
                            homeViewModel.toggleSelectedAllergyData(uiStates.selectedAllergy.find { all -> all.name == it }!!
                            )
                        })
                }

            }
            Spacer(modifier = Modifier.height(16.sdp))


        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextButton(onClick = { homeViewModel.toggleCurrentScreen(3) }) {
                Text(text = "Back", color = Orange, fontSize = 14.ssp)
            }
            DailyVitaButton(
                text = "Next",

                ) {
                homeViewModel.toggleCurrentScreen(5)
            }
        }

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp),
            progress = 0.7F,
            color = DarkBlue
        )
    }
}