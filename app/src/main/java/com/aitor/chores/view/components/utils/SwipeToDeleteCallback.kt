package com.aitor.chores.view.components.utils

import android.app.AlertDialog
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDeleteCallback<T, VH : RecyclerView.ViewHolder>(
    private val adapter: RecyclerViewAdapter<T, VH>,
    private val recyclerView: RecyclerView,
    private val onDelete: (T, Int) -> Unit,
    private val onShowDescriptionSnackbar: (T) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val deleteBackgroundColor = Color.RED
    private val infoBackgroundColor = Color.parseColor("#ADD8E6") // Azul Pastel
    private val infoTextColor = Color.BLACK
    private val textPaint = Paint().apply {
        color = infoTextColor
        textSize = 24f
        isAntiAlias = true
    }
    private val backgroundPaint = Paint()
    var recentlySwipedItemPosition: Int = RecyclerView.NO_POSITION

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val item = adapter.getItem(position)
        when (direction) {
            ItemTouchHelper.RIGHT -> {
                // Lógica de borrado (sin cambios)
                AlertDialog.Builder(recyclerView.context)
                    .setTitle("Confirmar Eliminación")
                    .setMessage("¿Estás seguro de que quieres borrar este elemento?")
                    .setPositiveButton("Sí") { dialog, _ ->
                        adapter.removeItem(position)
                        onDelete(item, position)
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        recyclerView.adapter?.notifyItemChanged(position)
                        dialog.dismiss()
                    }
                    .setOnCancelListener { recyclerView.adapter?.notifyItemChanged(position) }
                    .show()
            }
            ItemTouchHelper.LEFT -> {
                // Mostrar Snackbar al completar el swipe
                onShowDescriptionSnackbar(item)
                // Opcionalmente, podrías resetear la vista del item si quieres
                recyclerView.adapter?.notifyItemChanged(position)
            }
        }
    }
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX > 0) {
                // Borrado (derecha) - progresivo rojo
                backgroundPaint.color = deleteBackgroundColor
                c.drawRect(
                    itemView.left.toFloat(),
                    itemView.top.toFloat(),
                    dX,
                    itemView.bottom.toFloat(),
                    backgroundPaint
                )
            } else if (dX < 0) {
                // Información (izquierda) - progresivo azul
                backgroundPaint.color = infoBackgroundColor
                c.drawRect(
                    itemView.right.toFloat() + dX, // Empieza desde la derecha y se mueve a la izquierda
                    itemView.top.toFloat(),
                    itemView.right.toFloat(),
                    itemView.bottom.toFloat(),
                    backgroundPaint
                )
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}