package com.example.kwears.ui.product

import androidx.recyclerview.widget.DiffUtil
import com.example.kwears.data.WcResponse

object ProductDiffCallback: DiffUtil.ItemCallback<WcResponse> (){
    override fun areItemsTheSame(oldItem: WcResponse, newItem: WcResponse): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: WcResponse, newItem: WcResponse): Boolean =
        oldItem == newItem
}