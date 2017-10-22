package com.enrico.heroshell.ui.home

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import com.enrico.heroshell.Activities.ContainerActivity
import com.enrico.heroshell.R
import com.enrico.heroshell.data.HomeData
import org.jetbrains.anko.*

/**
 * Created by enrico on 10/19/17.
 */
class HomeCell: RelativeLayout(ContainerActivity.getContext()) {
    var homeData: HomeData? = null
    var index: Int? = null

    lateinit var indexText: TextView
    lateinit var primaryTextView: TextView
    lateinit var secondaryTextView: TextView

    init {
        relativeLayout {
            lparams(matchParent, dip(48))
            indexText = textView {
                id = 9999
                gravity = Gravity.CENTER
                textSize = 16f
                textColor = ContextCompat.getColor(context, R.color.secondaryTextColor)
            }.lparams {
                width = dip(48)
                height = matchParent
            }
            primaryTextView = textView {
                id = 9998
                gravity = Gravity.CENTER
                textSize = 18f
                textColor = ContextCompat.getColor(context, R.color.mainTextColor)
            }.lparams(wrapContent, wrapContent) {
                rightOf(indexText)
                centerVertically()
            }
            secondaryTextView = textView {
                gravity = Gravity.CENTER
                textSize = 14f
                textColor = ContextCompat.getColor(context, R.color.secondaryTextColor)
            }.lparams(wrapContent, wrapContent) {
                leftMargin = dip(8)
                rightOf(primaryTextView)
                centerVertically()
            }
        }
    }

    fun setup() {
        indexText.text = index?.toString()
        primaryTextView.text = homeData?.primaryString
        secondaryTextView.text = homeData?.secondaryString
    }
}