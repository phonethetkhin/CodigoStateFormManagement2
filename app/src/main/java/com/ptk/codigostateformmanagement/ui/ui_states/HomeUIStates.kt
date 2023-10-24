package com.ptk.codigostateformmanagement.ui.ui_states

import com.ptk.codigostateformmanagement.model.AllergyItem
import com.ptk.codigostateformmanagement.model.DietItem
import com.ptk.codigostateformmanagement.model.HealthConcernItem

data class HomeUIStates(
    val currentScreen: Int = 1,

    //healthConcerns
    val healthConcernsData: ArrayList<HealthConcernItem> = arrayListOf(),
    val selectedHealthConcerns: ArrayList<HealthConcernItem> = arrayListOf(),
    val priorities: ArrayList<HealthConcernItem> = arrayListOf(),

    //diets
    val dietsData: ArrayList<DietItem> = arrayListOf(),
    val selectedDiets: ArrayList<DietItem> = arrayListOf(),

    //allergy
    val allergyText: String = "",
    val allergyData: ArrayList<AllergyItem> = arrayListOf(),
    val selectedAllergy: ArrayList<AllergyItem> = arrayListOf(),
    val suggestions: ArrayList<String> = arrayListOf(),

    //habits
    val selectedSunExposure: String = "Yes",
    val selectedSmoke: String = "No",
    val selectedAlcohol: String = "0-1",
    /* val isSunExposure: Boolean = true,
     val isSmoke: Boolean = false,
     val alcoholLimit: Int = 0,*/
)