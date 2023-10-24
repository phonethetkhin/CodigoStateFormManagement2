package com.ptk.codigostateformmanagement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FinalOutputModel(

    @SerialName("health_concerns")
    var healthConcerns: List<HealthConcernsItem> = listOf(),

    @SerialName("diets")
    var diets: List<DietResponseItem> = listOf(),

    @SerialName("is_daily_exposure")
    var isDailyExposure: Boolean? = null,

    @SerialName("is_smoke")
    var isSmoke: Boolean? = null,

    @SerialName("alcohol")
    var alcohol: String? = null,

    @SerialName("allergies")
    var allergies: List<AllergiesItem> = listOf(),
)

@Serializable
data class AllergiesItem(
    @SerialName("id")
    var id: Int? = null,

    @SerialName("name")
    var name: String? = null,


    )

@Serializable
data class HealthConcernsItem(
    @SerialName("id")
    var id: Int? = null,

    @SerialName("name")
    var name: String? = null,

    @SerialName("priority")
    var priority: Int? = null
)

@Serializable
data class DietResponseItem(

    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("tool_tip")
    val toolTip: String? = null,


    )