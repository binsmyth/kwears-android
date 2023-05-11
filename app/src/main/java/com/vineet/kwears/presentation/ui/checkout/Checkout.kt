package com.vineet.kwears.presentation.ui.checkout

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.withTransaction
import com.vineet.kwears.data.credential
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.data.network.Api
import com.vineet.kwears.data.network.dto.shippingmethodsdto.ShippingMethodsDto
import com.vineet.kwears.domain.model.CheckoutCartProducts

fun getDatabase(context: Context) : AppDatabase{
    return AppDatabase.getDatabase(context)
}


@Preview(showSystemUi = true)
@Composable
fun Checkout(){
    val context = LocalContext.current
    var addedCartProducts by remember{ mutableStateOf(listOf<CheckoutCartProducts>())}

    LaunchedEffect(Unit){
        getDatabase(context).withTransaction {
            val checkoutProducts = getDatabase(context).addCartProductDao().getCheckoutProduct()
            addedCartProducts = checkoutProducts
        }
    }
    MaterialTheme{
        Column{
            Column{
                Form()
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun Form(){
    var shippingMethods by remember{ mutableStateOf(listOf<ShippingMethodsDto>()) }
    var expanded by remember{ mutableStateOf(false) }
    var selectedOptionText = ""
    LaunchedEffect(Unit){
        shippingMethods = Api.getClient().getShippingMethods(credential)
    }

//    if(shippingMethods.isNotEmpty()){
//        selectedOptionText = shippingMethods[0].title
//    }

    Column{
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ){
            if (shippingMethods.isNotEmpty())
                TextField(
                    value = selectedOptionText,
                    onValueChange ={},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    modifier = Modifier.menuAnchor(),
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

            ExposedDropdownMenu(expanded = expanded,onDismissRequest = { expanded = false}) {
                shippingMethods.forEach{item ->
                    DropdownMenuItem(onClick = {
                        selectedOptionText = item.title
                        expanded = false
                    }) {
                        Text(text = item.title )
                    }
                }
            }
        }
    }

//    val textFieldInitValues = List(myLists.size){OrderDto()}
//    val valueStateList = remember{ mutableStateListOf<OrderDto>().apply{addAll(textFieldInitValues)}}
//    var firstName by remember {
//        mutableStateOf("Enter Text")
//    }

//    myLists.forEachIndexed{index,item ->
//        OutlinedTextField(
//            value = valueStateList[index],
//            onValueChange = { valueStateList[index] = it },
//            label = { Text(, color=Color.White) },
//            maxLines=2,
//            textStyle = TextStyle(color=Color.White, fontWeight = FontWeight.Bold)
//        )
//    }

    Button(onClick = {

    }) {
        Text(text = "Place Order")
    }
}