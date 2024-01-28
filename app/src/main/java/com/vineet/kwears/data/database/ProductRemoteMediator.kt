package com.vineet.kwears.data.database

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.credential
import com.vineet.kwears.data.database.dataentity.FakeWcResponse
import com.vineet.kwears.data.database.dataentity.Source
import com.vineet.kwears.data.network.Api
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator (
        private val database:AppDatabase,
        private val Api:Api
    ):RemoteMediator<Int, WcResponse>(){

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, WcResponse>
    ): MediatorResult {
        val productDao = database.productDao()

        return try{
            println("try is working")
            println(loadType)
            val loadKey = when(loadType){
                LoadType.REFRESH ->
                        null
                LoadType.PREPEND ->
                        return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND ->{
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    lastItem.id
                }
            }
            println("when")
            println(Api.getAllProducts(credential))
            val response =  Api.getProduct(loadKey, credential)
            println("getting response")
            println(response)
//            val response =  Api.getProduct(loadKey).map{a -> a.toWcResponse()}
//            val pictures = listOf(Source("https://picsum.photos/200/300"))
//            val newResponse = response.map{WcResponse(it.id,it.name,pictures,"",it.price.toString())}.toMutableList()
            database.withTransaction {
                if(loadType == LoadType.REFRESH){
                    productDao.insertAllProducts(response)
                }
            }
            MediatorResult.Success(
                endOfPaginationReached = response == null
            )
        }
        catch(e: IOException){
            MediatorResult.Error(e)
        }
        catch(e: HttpException){
            MediatorResult.Error(e)
        }
    }
}