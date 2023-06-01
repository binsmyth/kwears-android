package com.vineet.kwears.data.network.repository

import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.network.Api
import com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto.AllShippingMethodsFromZoneDto
import com.vineet.kwears.data.network.dto.paymentgatewaysdto.PaymentGatewaysDto
import com.vineet.kwears.data.network.dto.productdto.ProductDto
import com.vineet.kwears.data.network.dto.shippingmethodfromshippingzonedto.ShippingZoneFromShippingMethod
import com.vineet.kwears.data.network.dto.shippingmethodsdto.ShippingMethodsDto
import com.vineet.kwears.data.network.dto.shippingzonesdto.ShippingZonesDto

class ProductRepositoryImpl (private val api: Api): ProductRepository {
    override suspend fun getProducts(page: Int, creds: String): MutableList<WcResponse> {
        return api.getProduct(page,creds)
    }

    override suspend fun getAllProducts(creds: String): MutableList<ProductDto> {
        return api.getAllProducts(creds)
    }

    override suspend fun getShippingMethods(creds: String): List<ShippingMethodsDto> {
        return api.getShippingMethods(creds)
    }

    override suspend fun getPaymentGateways(creds:String): List<PaymentGatewaysDto> {
        return api.getPaymentGateways(creds)
    }

    override suspend fun getShippingZones(creds: String): List<ShippingZonesDto> {
        return api.getShippingZones(creds)
    }

    override suspend fun getMethodOfZone(creds: String, zoneId: String, methodId: String):ShippingZoneFromShippingMethod {
        return api.getMethodOfZone(creds,zoneId,methodId)
    }

    override suspend fun getAllShippingMethodFromZone(
        creds: String,
        zoneId: Int
    ): List<AllShippingMethodsFromZoneDto> {
        return api.getAllShippingMethodsOfZone(creds,zoneId)
    }
}