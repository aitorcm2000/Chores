package com.aitor.chores.view.rcv

import android.graphics.Rect
import android.view.View
import android.widget.GridLayout
import androidx.recyclerview.widget.RecyclerView

class RCV_Deco_Colum (
    private val espacioVertical : Int,
    private val espacioHorizontal : Int,
    private val columnas: Int,
    private val horientacion: Int = GridLayout.VERTICAL
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //Ajustes para padding del RecyclerView
        with(outRect){
            if(horientacion == GridLayout.VERTICAL){
                if (parent.getChildAdapterPosition(view) < columnas) {
                    top = espacioVertical
                }
                if (parent.getChildAdapterPosition(view) % columnas == 0) {
                    left = espacioHorizontal
                }
            } else {
                if (parent.getChildAdapterPosition(view) < columnas) {
                    left = espacioHorizontal
                }
                if (parent.getChildAdapterPosition(view) % columnas == 0) {
                    top = espacioVertical
                }
            }

            right = espacioHorizontal
            bottom = espacioVertical
        }
    }
}