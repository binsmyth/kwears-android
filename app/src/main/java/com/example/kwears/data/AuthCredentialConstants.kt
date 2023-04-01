package com.example.kwears.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

//Base url for docker server
const val username = "ck_db1b1f6f668f17f63774c7f0c4bd874ce897509f"
const val password = "cs_3bb4c1e2ce93b774611e8328ce0e8fce174dc60f"
const val baseurl = "https://doveair.org/"
const val credentials = username + ":" + password

@RequiresApi(Build.VERSION_CODES.O)
val basic = "Basic " +
        Base64.getEncoder().encodeToString(credentials.toByteArray())