package com.enrico.heroshell.ui.profile;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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
import com.enrico.heroshell.ui.userFollowing.UserFollowingFragment;
import com.enrico.heroshell.data.User;
import com.enrico.heroshell.R;
import com.enrico.heroshell.Util.Utilities;

/**
 * Created by enrico on 10/19/17.
 */
public class UserProfileFragment extends Fragment implements UserProfileContract.View {
    public static final String USERNAME_ARGUMENT = "username";

    private RelativeLayout header;
    private ImageView coverImage;
    private ImageView profileImage;
    private View onlineIndicator;
    private TextView username;
    private TextView displayName;
    private ImageView moreButton;
    private TextView followingYou;
    private LinearLayout belowHeader;
    private TextView leftButton;
    private TextView centerButton;
    private TextView rightButton;

    private User user;
    private UserProfileContract.Presenter presenter;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UserProfilePresenter(this);
        String username = getArguments().getString(USERNAME_ARGUMENT);
        user = new User(username);
        currentDrawerItem = ContainerActivity.getDrawerSelection();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        header = view.findViewById(R.id.up_header_layout);
        coverImage = view.findViewById(R.id.up_cover_picture);
        profileImage = view.findViewById(R.id.up_profile_image);
        onlineIndicator = view.findViewById(R.id.up_online_indicator);
        username = view.findViewById(R.id.up_name_text);
        displayName= view.findViewById(R.id.up_display_name_text);
        moreButton = view.findViewById(R.id.up_more_button);
        followingYou = view.findViewById(R.id.up_following_text);
        belowHeader = view.findViewById(R.id.up_below_header_layout);
        leftButton = view.findViewById(R.id.up_left_button);
        centerButton = view.findViewById(R.id.up_center_button);
        rightButton = view.findViewById(R.id.up_right_button);

        getUserProfile();
    }

    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.updateToolbarTitle("Profile");
        if (user.isLoggedInUser()) {
            ContainerActivity.updateDrawerSelection(ContainerActivity.PROFILE_ID);
        } else {
            ContainerActivity.updateDrawerSelection(currentDrawerItem);
        }
    }

    private long currentDrawerItem;
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void showProfile() {
        final int HEADER_HEIGHT = Utilities.getDisplayWidth(this.getActivity()) * 9 / 16;
        System.out.println(HEADER_HEIGHT);

        ((CollapsingToolbarLayout.LayoutParams) header.getLayoutParams()).height = HEADER_HEIGHT;

        Glide.with(getActivity())
                .load(user.getCoverImageUrl())
                .into(coverImage);

        Glide.with(getActivity())
                .load(user.getProfileImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(profileImage);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.circle_border, null);
        if (drawable != null) {
            int color = (user.getOnline()) ? ContextCompat.getColor(getContext(), R.color.colorAccent) : Color.GRAY;
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            onlineIndicator.setBackground(drawable);
        }

        String usernameString = "@" + user.getUsername();
        username.setText(usernameString);
        username.setShadowLayer(2f, 2f, 2f, Color.BLACK);

        displayName.setText(user.getDisplayName());
        displayName.setShadowLayer(2f, 2f, 2f, Color.BLACK);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Utilities.adjustColorAlpha(Color.BLACK, 0.5f));
        gd.setCornerRadius(10f);
        gd.setStroke(1, Utilities.adjustColorAlpha(Color.BLACK, 0.5f));
        moreButton.setColorFilter(Color.WHITE);
        moreButton.setBackground(gd);

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

        ((CollapsingToolbarLayout.LayoutParams) belowHeader.getLayoutParams()).topMargin = HEADER_HEIGHT;

        SpannableString followingCount = new SpannableString("FOLLOWING\n" + user.getFollowingCount());
        followingCount.setSpan(new RelativeSizeSpan(1.75f), 10, followingCount.length(), 0);
        leftButton.setText(followingCount);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFollowing();
            }
        });

        SpannableString followersCount = new SpannableString("FOLLOWERS\n" + user.getFollowersCount());
        followersCount.setSpan(new RelativeSizeSpan(1.75f), 10, followersCount.length(), 0);
        centerButton.setText(followersCount);

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

    private void getUserProfile() {
        if (user.getNeedsProfileGrab()) {
            presenter.getUser(user);
        } else {
            showProfile();
        }
    }
}
