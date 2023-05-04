package com.vineet.kwears.presentation.ui.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.vineet.kwears.data.ProductDataSource
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.data.database.ProductRemoteMediator
import com.vineet.kwears.data.network.Api
import com.vineet.kwears.data.network.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(app:Application, private val repo: ProductRepository): AndroidViewModel(app) {
    @OptIn(ExperimentalPagingApi::class)
    val productPager = Pager(
        PagingConfig(pageSize=10),
        remoteMediator = ProductRemoteMediator(AppDatabase.getDatabase(getApplication<Application>().applicationContext),Api.getClient())
    ){
        ProductDataSource(repo)
    }.flow.cachedIn(viewModelScope)
}