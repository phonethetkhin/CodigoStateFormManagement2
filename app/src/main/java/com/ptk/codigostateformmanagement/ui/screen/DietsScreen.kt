package com.ptk.codigostateformmanagement.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.ptk.codigostateformmanagement.ui.composables.DailyVitaButton
import com.ptk.codigostateformmanagement.ui.composables.DailyVitaCheckBox
import com.ptk.codigostateformmanagement.ui.theme.DarkBlue
import com.ptk.codigostateformmanagement.ui.theme.Green
import com.ptk.codigostateformmanagement.ui.theme.Orange
import com.ptk.codigostateformmanagement.util.showToast
import com.ptk.codigostateformmanagement.viewmodel.HomeViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DietsScreen(homeViewModel: HomeViewModel) {

    LaunchedEffect(key1 = "") {
        homeViewModel.loadDietsData()
    }
    val context = LocalContext.current
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
                text = buildAnnotatedString {
                    append("Select the diets you follow.")
                    withStyle(style = SpanStyle(color = Orange)) {
                        append("*")
                    }
                },

                fontSize = 16.ssp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 32.sdp)
            )
            Spacer(modifier = Modifier.height(8.sdp))
            Log.e("DietData1", uiStates.selectedDiets.toString())
            DailyVitaCheckBox(
                dietItems = uiStates.dietsData,
                selectedOptions = uiStates.selectedDiets
            ) { dietItem, selected ->
                Log.e("DietData2", dietItem.toString())
                Log.e("DietData2", selected.toString())
                homeViewModel.toggleSelectedDietsItem(dietItem, selected)


            }

            Spacer(modifier = Modifier.height(16.sdp))


        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextButton(onClick = { homeViewModel.toggleCurrentScreen(2) }) {
                Text(text = "Back", color = Orange, fontSize = 14.ssp)
            }
            DailyVitaButton(
                text = "Next",

                ) {
                if (uiStates.selectedDiets.filter { it.checked }.isEmpty()) {
                    context.showToast("Please select diets you follow")
                } else {
                    homeViewModel.toggleCurrentScreen(4)
                }
            }
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp), progress = 0.5F, color = DarkBlue
        )
    }
}