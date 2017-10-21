package com.enrico.heroshell.Fragments;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.Models.User;
import com.enrico.heroshell.R;
import com.enrico.heroshell.Util.Utilities;

/**
 * Created by enrico on 10/19/17.
 */
public class UserProfileFragment extends Fragment {

    private View mainLayout;
    private User user;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainLayout= inflater.inflate(R.layout.fragment_user_profile, container, false);
        if (user != null) {
            setup();
        }
        return mainLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.updateToolbarTitle("Profile");
        ContainerActivity.updateDrawerSelection(ContainerActivity.PROFILE_ID);
    }

    public void setup() {
        final int HEADER_HEIGHT = Utilities.getDisplayWidth(this.getActivity()) * 9 / 16;
        System.out.println(HEADER_HEIGHT);

        RelativeLayout header = mainLayout.findViewById(R.id.up_header_layout);
        ((CollapsingToolbarLayout.LayoutParams) header.getLayoutParams()).height = HEADER_HEIGHT;

        ImageView coverImage = (ImageView) mainLayout.findViewById(R.id.up_cover_picture);
        Glide.with(getActivity())
                .load(user.getCoverImageUrl())
                .into(coverImage);

        ImageView profileImage = (ImageView) mainLayout.findViewById(R.id.up_profile_image);
        Glide.with(getActivity())
                .load(user.getProfileImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(profileImage);

        View onlineIndicator = mainLayout.findViewById(R.id.up_online_indicator);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.circle_border, null);
        if (drawable != null) {
            int color = (user.getOnline()) ? ContextCompat.getColor(getContext(), R.color.colorAccent) : Color.GRAY;
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            onlineIndicator.setBackground(drawable);
        }

        TextView username = (TextView) mainLayout.findViewById(R.id.up_name_text);
        username.setText(user.getUsername());
        username.setShadowLayer(2f, 2f, 2f, Color.BLACK);

        TextView displayName = (TextView) mainLayout.findViewById(R.id.up_display_name_text);
        displayName.setText(user.getDisplayName());
        displayName.setShadowLayer(2f, 2f, 2f, Color.BLACK);

        ImageView moreButton = (ImageView) mainLayout.findViewById(R.id.up_more_button);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Utilities.adjustColorAlpha(Color.BLACK, 0.5f));
        gd.setCornerRadius(10f);
        gd.setStroke(1, Utilities.adjustColorAlpha(Color.BLACK, 0.5f));
        moreButton.setColorFilter(Color.WHITE);
        moreButton.setBackground(gd);

        TextView followingYou = (TextView) mainLayout.findViewById(R.id.up_following_text);
        GradientDrawable gd2 = new GradientDrawable();
        gd2.setColor(Utilities.adjustColorAlpha(Color.BLACK, 0.5f));
        gd2.setCornerRadius(10f);
        gd2.setStroke(1, Utilities.adjustColorAlpha(Color.BLACK, 0.5f));
        followingYou.setBackground(gd2);
        if (user.getFollowingYou()) {
            followingYou.setVisibility(View.VISIBLE);
        } else {
            followingYou.setVisibility(View.GONE);
        }

        LinearLayout belowHeader = (LinearLayout) mainLayout.findViewById(R.id.up_below_header_layout);
        ((CollapsingToolbarLayout.LayoutParams) belowHeader.getLayoutParams()).topMargin = HEADER_HEIGHT;

        TextView leftButton = (TextView) mainLayout.findViewById(R.id.up_left_button);
        SpannableString followingCount = new SpannableString("FOLLOWING\n" + user.getFollowingCount());
        followingCount.setSpan(new RelativeSizeSpan(1.75f), 10, followingCount.length(), 0);
        leftButton.setText(followingCount);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _) {
                goToFollowing();
            }
        });

        TextView centerButton = (TextView) mainLayout.findViewById(R.id.up_center_button);
        SpannableString followersCount = new SpannableString("FOLLOWERS\n" + user.getFollowersCount());
        followersCount.setSpan(new RelativeSizeSpan(1.75f), 10, followersCount.length(), 0);
        centerButton.setText(followersCount);

        TextView rightButton = (TextView) mainLayout.findViewById(R.id.up_right_button);
        SpannableString playlistCount = new SpannableString("PLAYLISTS\n" + user.getPlaylistCount());
        playlistCount.setSpan(new RelativeSizeSpan(1.75f), 10, playlistCount.length(), 0);
        rightButton.setText(playlistCount);
    }

    /**
     * Method to go to following fragment when following button clicked
     */
    public void goToFollowing() {
        UserFollowingFragment userFollowingFragment = new UserFollowingFragment();
        ContainerActivity.pushFragment(userFollowingFragment);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
