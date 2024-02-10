package com.example.tinkofflabproject.utils

import android.content.res.Resources
import android.view.View
import android.view.animation.AlphaAnimation

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun View.startAlphaAnimation(isVisible: Boolean, duration: Long) {
    val alphaAnimation = if (isVisible) {
        AlphaAnimation(0f, 1f)
    } else {
        AlphaAnimation(1f, 0f)
    }

    alphaAnimation.duration = duration
    alphaAnimation.fillAfter = true
    this.startAnimation(alphaAnimation)

}