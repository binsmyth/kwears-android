package com.vineet.kwears.data.network.dto.createorderresponsedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaxLine(
    @Json(name = "compound")
    val compound: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "label")
    val label: String,
    @Json(name = "meta_data")
    val metaData: List<Any>,
    @Json(name = "rate_code")
    val rateCode: String,
    @Json(name = "rate_id")
    val rateId: Int,
    @Json(name = "shipping_tax_total")
    val shippingTaxTotal: String,
    @Json(name = "tax_total")
    val taxTotal: String
)