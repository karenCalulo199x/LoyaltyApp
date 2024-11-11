package com.kcals.loyaltyapp.ext

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun onSNACK(view: View, message: String) {
    val snackBarView = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    val view = snackBarView.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.BOTTOM
    params.setMargins(0, 0, 0, 350)
    view.layoutParams = params
    view.setBackgroundColor(Color.MAGENTA)
    snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
    snackBarView.show()
}