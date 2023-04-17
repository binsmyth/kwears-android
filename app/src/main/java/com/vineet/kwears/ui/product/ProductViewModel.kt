package com.vineet.kwears.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.vineet.kwears.data.ProductDataSource
import com.vineet.kwears.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repo: ProductRepository): ViewModel() {
    val productPager = Pager(
        PagingConfig(pageSize=10)
    ){
        ProductDataSource(repo)
    }.flow.cachedIn(viewModelScope)




//    private val _list = MutableLiveData<MutableList<WcResponse>?>()
//    val list: MutableLiveData<MutableList<WcResponse>?> = _list
//
//    suspend fun getApiProduct(page:Int=2,cred:String = credential){
//        _list.value =  Api.getClient().getProduct(page, cred)
//    }
}