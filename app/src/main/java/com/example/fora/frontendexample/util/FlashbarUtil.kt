package com.example.fora.frontendexample.util

import android.app.Activity
import android.content.Context
import android.graphics.PorterDuff
import android.widget.ImageView
import com.andrognito.flashbar.Flashbar
import com.andrognito.flashbar.anim.FlashAnimBarBuilder
import com.example.fora.frontendexample.R
import com.example.fora.frontendexample.retrofit.ServerError

fun buildProgressbar(activity: Activity?): Flashbar? {
    activity?.let {
        return Flashbar.Builder(it)
                .styleFlashbar(it.getString(R.string.loading))
                .showProgress(Flashbar.ProgressPosition.RIGHT)
                .progressTintRes(R.color.colorAccent)
                .duration(10000)
                .build()
    }
    return null
}

private fun Flashbar.Builder.styleFlashbar(message: String): Flashbar.Builder {
    gravity(Flashbar.Gravity.BOTTOM)
    messageColorRes(R.color.colorAccent)
    backgroundColorRes(R.color.colorPrimaryDark)
    message(message)
    return this
}

private fun Flashbar.Builder.animateFlashbar(context: Context): Flashbar.Builder {
    enterAnimation(FlashAnimBarBuilder(context).slideFromLeft())
    exitAnimation(FlashAnimBarBuilder(context).slideFromRight())
    return this
}

fun buildErrorToolbar(activity: Activity?, serverError: ServerError): Flashbar? {
    activity?.let {
        return Flashbar.Builder(it)
                .styleFlashbar(serverError.message)
                .animateFlashbar(activity)
                .icon(R.drawable.ic_error_outline_black_24dp)
                .showIcon(1f, ImageView.ScaleType.CENTER)
                .iconColorFilterRes(android.R.color.black, PorterDuff.Mode.SRC_IN)
                .duration(3000)
                .build()
    }
    return null
}
