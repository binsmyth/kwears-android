package com.vineet.kwears.presentation.composeproviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vineet.kwears.domain.model.CartProducts
import com.vineet.kwears.data.database.dataentity.Source

class SampleCartProductProvider: PreviewParameterProvider<CartProducts> {
    override val values = sequenceOf(CartProducts(2,999,"Sample Product",listOf(Source(src="https://picsum.photos/200"))))
}