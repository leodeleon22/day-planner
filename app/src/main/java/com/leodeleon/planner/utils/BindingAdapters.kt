package com.leodeleon.planner.utils

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.leodeleon.planner.R
import splitties.resources.drawable

object BindingAdapters {
    @BindingAdapter("isVisible")
    @JvmStatic
    fun setVisibility(view: View, isVisible: Boolean) {
        view.isVisible = isVisible
    }

    @BindingAdapter("loading")
    @JvmStatic
    fun animateBackground(view: TextView, loading: Boolean) {
        if (loading) {
            val bg = view.drawable(R.drawable.root_calling_gradient) as? AnimationDrawable
            view.background = bg
            bg?.apply {
                setEnterFadeDuration(500)
                setExitFadeDuration(500)
                start()
            }
        } else {
            (view.background as? AnimationDrawable)?.stop()
            view.background = null
        }
    }
}
