package com.enrico.heroshell.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.enrico.heroshell.Activities.ContainerActivity
import com.enrico.heroshell.data.User
import com.enrico.heroshell.R
import org.jetbrains.anko.*

/**
 * Created by enrico on 10/19/17.
 */
class UserCell: RelativeLayout(ContainerActivity.getContext()) {
    var user: User? = null

    lateinit var profileImage: ImageView
    lateinit var onlineIndicator: View
    lateinit var usernameText: TextView
    lateinit var displayNameText: TextView

    init {
        relativeLayout {
            lparams {
                width = matchParent
                height = dip(76)
            }
            profileImage = imageView {
                id = 9999
                scaleType = ImageView.ScaleType.FIT_CENTER
            }.lparams(dip(60), dip(60)) {
                margin = dip(8)
            }
            onlineIndicator = view {

            }.lparams(dip(60), dip(60)) {
                margin = dip(8)
            }
            val space = space {
                id = 9998
            }.lparams(0, 0) {
                centerVertically()
            }
            displayNameText = textView {
                gravity = Gravity.CENTER
                textSize = 18f
                textColor = ContextCompat.getColor(context, R.color.mainTextColor)
            }.lparams(wrapContent, wrapContent) {
                rightOf(profileImage)
                above(space)
            }
            usernameText = textView {
                gravity = Gravity.CENTER
                textSize = 14f
                textColor = ContextCompat.getColor(context, R.color.secondaryTextColor)
            }.lparams(wrapContent, wrapContent) {
                rightOf(profileImage)
                below(space)
            }
        }
    }

    fun setup() {
        Glide.with(context)
                .load(user?.profileImageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(profileImage)

        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.circle_border, null)
        if (drawable != null) {
            val color = if (user?.online == true) ContextCompat.getColor(context, R.color.colorAccent) else Color.GRAY
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            onlineIndicator.background = drawable
        }

        displayNameText.text = user?.displayName
        val username = "@${user?.username}"
        usernameText.text = username
    }
}