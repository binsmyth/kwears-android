package com.example.kwears.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kwears.data.WcResponse
import com.example.kwears.network.getApiData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {
    val gson = Gson()
   private val _text = MutableLiveData<List<WcResponse>>().apply{
       getApiData()?.enqueue(object : Callback<List<WcResponse>> {
           override fun onResponse(call: Call<List<WcResponse>>, response: Response<List<WcResponse>>) {
               Log.v("debug-dataResponse", response.body().toString())
               if (response.isSuccessful) {
                   value = response.body()
                   Log.v("success", (response.body() is List<WcResponse>).toString())
                   // do something with the users list
               } else {
                   // handle error response
                   Log.v("Call success but response error", response.toString())
               }
           }
           override fun onFailure(call: Call<List<WcResponse>>, t: Throwable) {
               // handle failure
               Log.v("Call unsuccessful",t.toString())
           }

       })
   }
//    val text: LiveData<String> = _text
    val list: LiveData<List<WcResponse>> = _text
}