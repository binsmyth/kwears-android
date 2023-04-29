package com.vineet.kwears.presentation.ui.product

import androidx.recyclerview.widget.DiffUtil
import com.vineet.kwears.data.database.dataentity.WcResponse

object ProductDiffCallback: DiffUtil.ItemCallback<WcResponse> (){
    override fun areItemsTheSame(oldItem: WcResponse, newItem: WcResponse): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: WcResponse, newItem: WcResponse): Boolean =
        oldItem == newItem
}