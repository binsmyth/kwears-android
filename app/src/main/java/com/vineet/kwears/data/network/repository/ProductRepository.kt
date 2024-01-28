package com.vineet.kwears.data.network.repository

import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto.AllShippingMethodsFromZoneDto
import com.vineet.kwears.data.network.dto.createorderresponsedto.CreateOrderResponseDto
import com.vineet.kwears.data.network.dto.orderdto.OrderDto
import com.vineet.kwears.data.network.dto.paymentgatewaysdto.PaymentGatewaysDto
import com.vineet.kwears.data.network.dto.productdto.ProductDto
import com.vineet.kwears.data.network.dto.shippingmethoddetailsfromzonedto.ShippingMethodDetailsFromZoneDto
import com.vineet.kwears.data.network.dto.shippingmethodsdto.ShippingMethodsDto
import com.vineet.kwears.data.network.dto.shippingzonesdto.ShippingZonesDto

interface ProductRepository{
    suspend fun getProducts(page:Int,creds:String):MutableList<WcResponse>
    suspend fun getAllProducts(creds:String):MutableList<ProductDto>

    //For getting shipping methods
    suspend fun getShippingMethods(creds:String):List<ShippingMethodsDto>

    //For getting payment gateways
    suspend fun getPaymentGateways(creds: String):List<PaymentGatewaysDto>

    //For getting shipping zones
    suspend fun getShippingZones(creds:String):List<ShippingZonesDto>

    //For getting particular shipping methods from particular zone
    suspend fun getShippingMethodDetailsOfZone(creds:String,zoneId:Int?,methodId:Int?):ShippingMethodDetailsFromZoneDto

    //For getting shipping zone methods
    suspend fun getAllShippingMethodFromZone(creds:String,zoneId:Int):List<AllShippingMethodsFromZoneDto>

    //Create an order
    suspend fun createOrder(creds:String,order:OrderDto):CreateOrderResponseDto
}