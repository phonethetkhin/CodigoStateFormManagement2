@file:OptIn(ExperimentalLayoutApi::class)

package com.ptk.codigostateformmanagement.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.ptk.codigostateformmanagement.ui.composables.DailyVitaButton
import com.ptk.codigostateformmanagement.ui.composables.HealthConcernsList
import com.ptk.codigostateformmanagement.ui.theme.DarkBlue
import com.ptk.codigostateformmanagement.ui.theme.Green
import com.ptk.codigostateformmanagement.ui.theme.Orange
import com.ptk.codigostateformmanagement.util.showToast
import com.ptk.codigostateformmanagement.viewmodel.HomeViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun HealthConcernsScreen(homeViewModel: HomeViewModel) {
    val uiStates by homeViewModel.uiStates.collectAsState()
    LaunchedEffect(key1 = "") {
        homeViewModel.loadHealthConcernsData()
    }
    val context = LocalContext.current

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
                text = buildAnnotatedString {
                    append("Select the top health concerns.")
                    withStyle(style = SpanStyle(color = Orange)) {
                        append("*")
                    }
                    append("\n(upto 5)")

                },

                fontSize = 16.ssp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 16.sdp)
            )

            Log.e("healthConcernData1", uiStates.priorities.toString())

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(7.sdp),
                modifier = Modifier
                    .padding(7.sdp)
            ) {
                uiStates.selectedHealthConcerns.forEach { healthConcern ->
                    val color = if (healthConcern.checked) DarkBlue else Color.Transparent
                    val textColor = if (healthConcern.checked) Color.White else DarkBlue
                    val border = if (healthConcern.checked) 0.sdp else 1.sdp
                    Text(
                        modifier = Modifier
                            .padding(top = 8.sdp)
                            .background(color = color, shape = CircleShape)
                            .border(width = border, color = DarkBlue, shape = CircleShape)
                            .clickable {
                                if (uiStates.selectedHealthConcerns.filter { it.checked }.size > 4) {
                                    context.showToast("Cannot select more than 5")
                                } else {
                                    homeViewModel.toggleSelectedHealthConcerns(healthConcern)
                                }
                            }
                            .padding(vertical = 4.sdp, horizontal = 16.sdp),

                        text = healthConcern.name,
                        fontSize = 11.ssp,
                        color = textColor,

                        )
                }
            }
            Text(
                text = "Prioritize",
                fontSize = 16.ssp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 32.sdp)
            )
            Spacer(modifier = Modifier.height(4.sdp))
            HealthConcernsList(
                uiStates.priorities.map { it.name }) { from, to ->
                Log.e("healthConcernData2", from.toString())
                Log.e("healthConcernData2", to.toString())
                homeViewModel.togglePriorities(to, from)
            }


        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextButton(onClick = { homeViewModel.toggleCurrentScreen(1) }) {
                Text(text = "Back", color = Orange, fontSize = 14.ssp)
            }
            DailyVitaButton(
                text = "Next",

                ) {
                if (uiStates.selectedHealthConcerns.filter { it.checked }.isNotEmpty()) {
                    homeViewModel.toggleCurrentScreen(3)
                } else {
                    context.showToast("Please select health concerns")
                }
            }
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp), progress = 0.2F, color = DarkBlue
        )
    }
}