package com.ptk.codigostateformmanagement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DietsModel(

    @SerialName("data")
    val data: List<DietItem> = listOf()
)

@Serializable
data class DietItem(

    @SerialName("tool_tip")
    val toolTip: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("checked")
    val checked: Boolean = false,
)
