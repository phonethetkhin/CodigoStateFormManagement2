package com.ptk.codigostateformmanagement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HealthConcernModel(

    @SerialName("data")
    val data: List<HealthConcernItem> = listOf()
)

@Serializable
data class HealthConcernItem(

    @SerialName("name")
    val name: String = "",

    @SerialName("id")
    val id: Int? = null,

    @SerialName("checked")
    val checked: Boolean = false,

    @SerialName("recompose")
    val recompose: Boolean = false,
)