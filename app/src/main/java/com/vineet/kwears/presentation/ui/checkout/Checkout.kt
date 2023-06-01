package com.vineet.kwears.presentation.ui.checkout

import DropdownGeneric
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.withTransaction
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.domain.model.AllShippingMethodsFromZone
import com.vineet.kwears.domain.model.CheckoutCartProducts
import com.vineet.kwears.domain.model.CreateOrderPaymentMethod
import com.vineet.kwears.domain.model.ShippingZonesCheckout
import kotlinx.coroutines.flow.flowOf

val viewModel = CheckoutViewModel()
var checkShippingZoneChange by mutableStateOf("")
fun getDatabase(context: Context) : AppDatabase{
    return AppDatabase.getDatabase(context)
}
@Preview(showSystemUi = true)
@Composable
fun Checkout(){
    val context = LocalContext.current
    //to get added cart products
    var addedCartProducts by remember{ mutableStateOf(listOf<CheckoutCartProducts>())}
    //to get payment gateway methods
    var paymentGatewaysDetails by remember{ mutableStateOf(listOf<CreateOrderPaymentMethod>())}
    //to get shipping methods available
    var shippingDetails by remember{ mutableStateOf(listOf<AllShippingMethodsFromZone>() as List<AllShippingMethodsFromZone>?) }
    //to get shipping zones
    var shippingZones by remember{ mutableStateOf(listOf<ShippingZonesCheckout>()) }


    LaunchedEffect(Unit){
        Log.d("Debugging Launched Effect for payment Gateway","This launched effect is called")
        getDatabase(context).withTransaction {
            val checkoutProducts = getDatabase(context).addCartProductDao().getCheckoutProduct()
            addedCartProducts = checkoutProducts
        }
        paymentGatewaysDetails = viewModel.getPaymentGatewayDetails()
    }

    LaunchedEffect(viewModel.selectedShippingZoneItems){
        Log.d("Debugging Launched Effect","shippingZones changed " + viewModel.selectedShippingZoneItems)
        shippingZones = viewModel.getShippingZones()
        shippingDetails = viewModel.getAllShippingMethodsFromZone()
    }

    MaterialTheme{
        Column{
            Column{
                //User Details
                UserDetails()

                //Payment methods
                DropdownGeneric(
                    label = "Payment Methods",
                    itemsFlow = flowOf(paymentGatewaysDetails),
                    selectedItem = viewModel.selectedPaymentMethodItem,
                    onItemSelected = {item -> viewModel.selectedPaymentMethodItem = item},
                    itemToString = {item -> item.payment_method_title}
                )
                //Shipping methods
                DropdownGeneric(
                    label = "Shipping",
                    itemsFlow = flowOf(shippingDetails),
                    selectedItem = viewModel.selectedShippingMethodItem,
                    onItemSelected = { item -> viewModel.selectedShippingMethodItem = item },
                    itemToString = {item -> item.ShippingTitle}
                )
                //Shipping Zones
                DropdownGeneric(
                    label="Zones",
                    itemsFlow= flowOf(shippingZones),
                    selectedItem = viewModel.selectedShippingZoneItems,
                    onItemSelected = { item ->
                                        viewModel.selectedShippingZoneItems = item
                                        checkShippingZoneChange = item.ShippingName
                                     },
                    itemToString = {item -> item.ShippingName}
                )

                Button(onClick = {viewModel.submitCheckout(context)}) {
                    Text(text="Place order")
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetails(){
    Column{
        //First Name
        TextField(
            value = viewModel.firstname,
            label={Text("First Name")},
            onValueChange = {newValue ->
                viewModel.firstname = newValue
            }
        )
        //Last Name
        TextField(
            value = viewModel.lastname,
            label={Text("Last Name")},
            onValueChange = {newValue ->
                viewModel.lastname = newValue
            }
        )
        //Street Name
        TextField(
            value = viewModel.address1,
            label={Text("Address 1")},
            onValueChange = {newValue ->
                viewModel.address1 = newValue
            }
        )
        //House no.
        TextField(
            value = viewModel.address2,
            label={Text("Address 2")},
            onValueChange = {newValue ->
                viewModel.address2 = newValue
            }
        )

        //City / Locality
        TextField(
            value = viewModel.city,
            label={Text("City")},
            onValueChange = {newValue ->
                viewModel.city = newValue
            }
        )
        //District
        TextField(
            value = viewModel.district,
            label={Text("District")},
            onValueChange = {newValue ->
                viewModel.district = newValue
            }
        )
        //postcode
        TextField(
            value = viewModel.postcode,
            label={Text("Postcode")},
            onValueChange = {newValue ->
                viewModel.postcode = newValue
            }
        )

        TextField(
            value = viewModel.phone,
            label={Text("Phone No.")},
            onValueChange = {newValue ->
                viewModel.phone = newValue
            }
        )
    }
}