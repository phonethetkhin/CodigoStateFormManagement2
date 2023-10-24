package com.ptk.codigostateformmanagement.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.ptk.codigostateformmanagement.R
import com.ptk.codigostateformmanagement.model.AllergyItem
import com.ptk.codigostateformmanagement.model.AllergyModel
import com.ptk.codigostateformmanagement.model.DietItem
import com.ptk.codigostateformmanagement.model.DietsModel
import com.ptk.codigostateformmanagement.model.HealthConcernItem
import com.ptk.codigostateformmanagement.model.HealthConcernModel
import com.ptk.codigostateformmanagement.ui.ui_states.HomeUIStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val application: Application) : ViewModel() {
    private val _uiStates = MutableStateFlow(HomeUIStates())
    val uiStates = _uiStates.asStateFlow()

//====================================toggle states=================================================

    fun toggleCurrentScreen(currentScreen: Int) {
        _uiStates.update { it.copy(currentScreen = currentScreen) }
    }

    fun toggleSelectedHealthConcerns(healthConcernItem: HealthConcernItem) {
        _uiStates.update {
            it.copy(selectedHealthConcerns = _uiStates.value.selectedHealthConcerns.mapIndexed { index, details ->
                if (_uiStates.value.selectedHealthConcerns.indexOf(_uiStates.value.selectedHealthConcerns.find { it.id == healthConcernItem.id }) == index) details.copy(
                    checked = !details.checked
                )
                else details
            } as ArrayList<HealthConcernItem>)
        }
        _uiStates.update {
            it.copy(priorities = _uiStates.value.selectedHealthConcerns.filter { it.checked }
                .toCollection(ArrayList()))
        }
        _uiStates.update {
            it.copy(selectedHealthConcerns = _uiStates.value.selectedHealthConcerns.mapIndexed { index, details ->
                if (_uiStates.value.selectedHealthConcerns.indexOf(_uiStates.value.selectedHealthConcerns.find { it.id == healthConcernItem.id }) == index) details.copy(
                    recompose = !details.recompose
                )
                else details
            } as ArrayList<HealthConcernItem>)
        }



        Log.e("healthConcernData", uiStates.value.selectedHealthConcerns.toString())

    }

    fun togglePriorities(toIndex: Int, fromIndex: Int) {
        _uiStates.update {
            _uiStates.value.priorities.apply {
                add(toIndex, removeAt(fromIndex))
            }
            it.copy(priorities = _uiStates.value.priorities.mapIndexed { index, details ->
                if (_uiStates.value.priorities[fromIndex].id == details.id) details.copy(
                    recompose = !details.recompose
                )
                else details
            } as ArrayList<HealthConcernItem>)
        }
        Log.e("healthConcernData", _uiStates.value.priorities.toString())

    }

    fun toggleSelectedDietsItem(dietItem: DietItem, selected: Boolean) {
        _uiStates.update {
            it.copy(selectedDiets = _uiStates.value.selectedDiets.mapIndexed { index, details ->
                if (_uiStates.value.selectedDiets.indexOf(_uiStates.value.selectedDiets.find { it.id == dietItem.id }) == index) details.copy(
                    checked = selected
                )
                else details
            } as ArrayList<DietItem>)
        }

        Log.e("DietData", uiStates.value.selectedDiets.toString())

    }

    fun toggleSelectedSunExposure(selectedSunExposure: String) {
        _uiStates.update { it.copy(selectedSunExposure = selectedSunExposure) }
    }

    fun toggleSelectedSmoke(selectedSmoke: String) {
        _uiStates.update { it.copy(selectedSmoke = selectedSmoke) }
    }

    fun toggleSelectedAlcohol(selectedAlcoholLimit: String) {
        _uiStates.update { it.copy(selectedAlcohol = selectedAlcoholLimit) }
    }

    fun toggleAllergyText(allergyText: String) {
        if (allergyText.trim().isNotEmpty()) {
            val suggestionsList = _uiStates.value.allergyData.filter {
                it.name.toLowerCase().contains(allergyText.toLowerCase())
            }
            if (suggestionsList.isNotEmpty()) {
                _uiStates.update {
                    it.copy(
                        suggestions = suggestionsList.map { sugg -> sugg.name }
                            .toCollection(ArrayList())
                    )
                }
            } else {
                _uiStates.update {
                    it.copy(
                        suggestions = arrayListOf()
                    )
                }
            }
        } else {
            _uiStates.update {
                it.copy(
                    suggestions = arrayListOf()
                )
            }
        }
        _uiStates.update { it.copy(allergyText = allergyText) }

    }

    fun toggleSelectedAllergyData(allergyItem: AllergyItem) {
        _uiStates.update {
            it.copy(selectedAllergy = _uiStates.value.selectedAllergy.mapIndexed { index, details ->
                if (_uiStates.value.selectedAllergy.indexOf(_uiStates.value.selectedAllergy.find { it.id == allergyItem.id }) == index) details.copy(
                    checked = true
                )
                else details
            } as ArrayList<AllergyItem>)
        }
    }

    //======================================load json===================================================
    fun loadHealthConcernsData() {
        val raw: InputStream = application.resources.openRawResource(R.raw.health_concern)
        val rd: Reader = BufferedReader(InputStreamReader(raw))
        val gson = Gson()
        val healthConcernModel: HealthConcernModel =
            gson.fromJson(rd, HealthConcernModel::class.java)

        val list = healthConcernModel.data.toCollection(ArrayList())
        list.add(HealthConcernItem("Special Medical Condition", 0, false))
        _uiStates.update {
            it.copy(
                healthConcernsData = list,
                selectedHealthConcerns = list
            )
        }
        _uiStates.update {
            it.copy(priorities = _uiStates.value.selectedHealthConcerns.filter { it.checked }
                .toCollection(ArrayList()))
        }
    }

    fun loadDietsData() {
        val raw: InputStream = application.resources.openRawResource(R.raw.diets)
        val rd: Reader = BufferedReader(InputStreamReader(raw))
        val gson = Gson()
        val dietModel: DietsModel = gson.fromJson(rd, DietsModel::class.java)

        val list = dietModel.data.toCollection(ArrayList())
        list.add(0, DietItem("", "None", 0, true))
        _uiStates.update {
            it.copy(
                dietsData = list,
                selectedDiets = list
            )
        }
    }

    fun loadAllergyData() {
        val raw: InputStream = application.resources.openRawResource(R.raw.allergies)
        val rd: Reader = BufferedReader(InputStreamReader(raw))
        val gson = Gson()
        val allergyModel: AllergyModel =
            gson.fromJson(rd, AllergyModel::class.java)

        val list = allergyModel.data.toCollection(ArrayList())
        _uiStates.update {
            it.copy(
                allergyData = list,
                selectedAllergy = list
            )
        }
    }
}