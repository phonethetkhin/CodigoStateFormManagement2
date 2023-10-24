package com.ptk.codigostateformmanagement.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ptk.codigostateformmanagement.ui.ui_states.HomeUIStates
import com.ptk.codigostateformmanagement.viewmodel.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val uiStates by homeViewModel.uiStates.collectAsState()

    HomeScreenContent(homeViewModel, uiStates)
}

@Composable
fun HomeScreenContent(homeViewModel: HomeViewModel, uiStates: HomeUIStates) {
    if (uiStates.currentScreen == 1) {
        IntroductionScreen(homeViewModel)
    }

    if (uiStates.currentScreen == 2) {
        HealthConcernsScreen(homeViewModel)
    }

    if (uiStates.currentScreen == 3) {
        DietsScreen(homeViewModel)
    }

    if (uiStates.currentScreen == 4) {
        AllergyScreen(homeViewModel)
    }

    if (uiStates.currentScreen == 5) {
        HabitsScreen(homeViewModel)
    }

}


