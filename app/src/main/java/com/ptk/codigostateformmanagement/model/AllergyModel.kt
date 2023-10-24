package com.ptk.codigostateformmanagement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllergyModel(

    @SerialName("data")
    val data: List<AllergyItem> = listOf()
)

@Serializable
data class AllergyItem(

    @SerialName("name")
    val name: String = "",

    @SerialName("id")
    val id: Int? = null,

    @SerialName("checked")
    val checked: Boolean = false
)
