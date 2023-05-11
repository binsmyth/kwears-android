package com.vineet.kwears.data

import okhttp3.Credentials

//Base url for docker server
//const val username = "ck_db1b1f6f668f17f63774c7f0c4bd874ce897509f"
//const val password = "cs_3bb4c1e2ce93b774611e8328ce0e8fce174dc60f"
//const val baseurl = "https://doveair.org/"
const val username = "ck_a6ea0f0b1c7ffdecc6be81e256aa8c894498dfc0"
const val password = "cs_c97b38d3365200fbf71be26552366a5c5411e777"
const val baseurl = "https://192.168.1.102:8443"
const val fakebaseurl = "https://fakestoreapi.com/"

//const val credentials = username + ":" + password
//Creating a basic credentials for woocommerce api
val credential = Credentials.basic(username, password)