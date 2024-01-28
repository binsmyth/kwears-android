package com.vineet.kwears.data.network.dto.shippingmethoddetailsfromzonedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Settings(
    @Json(name = "class_cost_91")
    val classCost91: ClassCost91?,
    @Json(name = "class_cost_92")
    val classCost92: ClassCost91?,
    @Json(name = "class_costs")
    val classCosts: ClassCosts?,
    @Json(name = "cost")
    val cost: Cost?,
    @Json(name = "no_class_cost")
    val noClassCost: NoClassCost?,
    @Json(name = "tax_status")
    val taxStatus: TaxStatus?,
    @Json(name = "title")
    val title: Title?,
    @Json(name = "type")
    val type: Type?
)