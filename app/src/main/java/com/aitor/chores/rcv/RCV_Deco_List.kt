package com.aitor.chores.rcv

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RCV_Deco_List (private val espacio : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //Ajustes para padding del RecyclerView
        with(outRect){
            if(parent.getChildAdapterPosition(view) == 0){
                top = espacio
            }
            left = espacio
            right = espacio
            bottom = espacio
        }
    }
}