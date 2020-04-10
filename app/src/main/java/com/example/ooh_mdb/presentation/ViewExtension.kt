package com.example.ooh_mdb.presentation

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

val View.ctx: Context
    get() = context

//  Selected item recognition extension
fun <T : ViewHolder> T.onClick(event: (view: View, position: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition)
    }
    return this
}
