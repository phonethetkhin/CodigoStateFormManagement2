package com.ptk.codigostateformmanagement.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.ptk.codigostateformmanagement.model.AllergiesItem
import com.ptk.codigostateformmanagement.model.DietResponseItem
import com.ptk.codigostateformmanagement.model.FinalOutputModel
import com.ptk.codigostateformmanagement.model.HealthConcernsItem
import com.ptk.codigostateformmanagement.ui.composables.DailyVitaButton
import com.ptk.codigostateformmanagement.ui.composables.DailyVitaRadioButton
import com.ptk.codigostateformmanagement.ui.theme.DarkBlue
import com.ptk.codigostateformmanagement.ui.theme.Green
import com.ptk.codigostateformmanagement.ui.theme.Orange
import com.ptk.codigostateformmanagement.viewmodel.HomeViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun HabitsScreen(homeViewModel: HomeViewModel) {
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
                    append("Is your daily exposure to sun is limited?")
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
            DailyVitaRadioButton(radioOptions = listOf("Yes", "No"), uiStates.selectedSunExposure) {
                homeViewModel.toggleSelectedSunExposure(it)
            }

            Text(
                text = buildAnnotatedString {
                    append("Do you current smoke (tobacco or marijuana)?")
                    withStyle(style = SpanStyle(color = Orange)) {
                        append("*")
                    }
                },
                fontSize = 16.ssp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 16.sdp)
            )

            Spacer(modifier = Modifier.height(4.sdp))
            DailyVitaRadioButton(radioOptions = listOf("Yes", "No"), uiStates.selectedSmoke) {
                homeViewModel.toggleSelectedSmoke(it)

            }

            Text(
                text = buildAnnotatedString {
                    append("On average, how many alcoholic beverages do you have in a week?")
                    withStyle(style = SpanStyle(color = Orange)) {
                        append("*")
                    }
                },
                fontSize = 16.ssp,
                color = DarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp, top = 16.sdp)
            )

            Spacer(modifier = Modifier.height(8.sdp))
            DailyVitaRadioButton(
                radioOptions = listOf("0-1", "2-5", "5+"),
                uiStates.selectedAlcohol
            ) {
                homeViewModel.toggleSelectedAlcohol(it)

            }

            DailyVitaButton(
                text = "Get my personalized vitamin", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.sdp, horizontal = 16.sdp)
            ) {
                val finalOutputModel = FinalOutputModel()
                finalOutputModel.healthConcerns =
                    uiStates.selectedHealthConcerns.filter { it.checked }
                        .map {
                            HealthConcernsItem(
                                it.id,
                                it.name,
                                uiStates.priorities.indexOf(uiStates.priorities.find { prior -> prior.id == it.id }) + 1
                            )
                        }.sortedBy { it.priority }

                finalOutputModel.diets =
                    uiStates.selectedDiets.filter { it.id != 0 && it.checked }.map {
                        DietResponseItem(
                            it.id, it.name, it.toolTip,
                        )
                    }

                finalOutputModel.isDailyExposure =
                    uiStates.selectedSunExposure == "Yes"
                finalOutputModel.isSmoke =
                    uiStates.selectedSmoke == "Yes"
                finalOutputModel.alcohol = uiStates.selectedAlcohol


                finalOutputModel.allergies = uiStates.selectedAllergy.filter { it.checked }.map {
                    AllergiesItem(it.id, it.name)
                }

                Log.e("FinalOutput", finalOutputModel.toString())
            }
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp),
            progress = 1F,
            color = DarkBlue
        )
    }
}