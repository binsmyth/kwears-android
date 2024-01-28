package com.vineet.kwears

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vineet.kwears.data.network.Api
import com.vineet.kwears.presentation.ui.product.ProductDiffCallback
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.vineet.kwears", appContext.packageName)
    }
    @Test
    fun testWcApi(){
        val api = runBlocking {
            Api.getClient().testApi()
        }
        println(api)
    }
}