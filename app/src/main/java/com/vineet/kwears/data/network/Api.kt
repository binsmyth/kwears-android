package com.vineet.kwears.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vineet.kwears.data.TestResponse
import com.vineet.kwears.data.baseurl
import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto.AllShippingMethodsFromZoneDto
import com.vineet.kwears.data.network.dto.createorderresponsedto.CreateOrderResponseDto
import com.vineet.kwears.data.network.dto.orderdto.OrderDto
import com.vineet.kwears.data.network.dto.paymentgatewaysdto.PaymentGatewaysDto
import com.vineet.kwears.data.network.dto.productdto.ProductDto
import com.vineet.kwears.data.network.dto.shippingmethoddetailsfromzonedto.ShippingMethodDetailsFromZoneDto
import com.vineet.kwears.data.network.dto.shippingmethodsdto.ShippingMethodsDto
import com.vineet.kwears.data.network.dto.shippingzonesdto.ShippingZonesDto
import com.vineet.kwears.domain.model.CreateOrderPaymentMethod
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import javax.net.ssl.HostnameVerifier

interface Api{
    @GET("/wordpress/wp-json/wc/v3/products")
    suspend fun getProduct(@Query("page")page:Int?,@Header("Authorization")cred:String):MutableList<WcResponse>

//    @GET("/products")
//    suspend fun getProduct(@Query("page")page:Int?):MutableList<FakeWcResponse>

    //Getting all shipping zones
    @GET("/wordpress/wp-json/wc/v3/shipping/zones")
    suspend fun getShippingZones(@Header("Authorization")cred:String):List<ShippingZonesDto>

    //For listing all payment gateways
    @GET("wordpress/wp-json/wc/v3/payment_gateways")
    suspend fun getPaymentGateways(@Header("Authorization")cred:String):List<PaymentGatewaysDto>

    //For creating orders
    @POST("/wordpress/wp-json/wc/v3/orders")
    suspend fun createOrder(@Header("Authorization")cred:String, @Body order:OrderDto):CreateOrderResponseDto

    //Get all shipping zone
    @GET("/wordpress/wp-json/wc/v3/shipping_methods")
    suspend fun getShippingMethods(@Header("Authorization")cred:String):List<ShippingMethodsDto>

    //Get all shipping method in a shipping zone
    @GET("/wordpress/wp-json/wc/v3/shipping/zones/{zoneid}/methods")
    suspend fun getAllShippingMethodsOfZone(@Header("Authorization")cred:String, @Path("zoneid")zoneId:Int?):List<AllShippingMethodsFromZoneDto>
    //Get a shipping method detail in a shipping zone
    @GET("/wordpress/wp-json/wc/v3/shipping/zones/{zoneid}/methods/{methodid}")
    suspend fun getShippingMethodDetailsOfZone(@Header("Authorization")cred:String,@Path("zoneid")zoneId:Int?,@Path("methodid")methodId:Int?): ShippingMethodDetailsFromZoneDto

    @GET("/wordpress/wp-json/wc/v3/products")
    suspend fun getAllProducts(@Header("Authorization")cred:String):MutableList<ProductDto>

    @GET("/wordpress/wp-json/wc/v3")
    suspend fun testApi():List<TestResponse>

    companion object {
        private lateinit var data: Api
        private fun buildMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }
        fun getClient(): Api {
            //A logging plugin for retrofit
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            //An interceptor for putting headers into retrofit
            val apInterceptor = Interceptor {
                val originalRequest = it.request()
                val newHttpUrl = originalRequest.url.newBuilder().build()


                val newRequest = originalRequest.newBuilder()
                    /*.headers(headers)*/
                    .url(newHttpUrl)
                    .build()

                it.proceed(newRequest)
            }

            val client = OkHttpClient().newBuilder()
                .addNetworkInterceptor(apInterceptor)
                .addNetworkInterceptor(logging)
                .build()
            data = Retrofit.Builder()
                .client(client)
                .baseUrl(baseurl)
                .addConverterFactory(MoshiConverterFactory.create(buildMoshi()))
                .build()
                .create(Api::class.java)

            return data
        }
    }
}