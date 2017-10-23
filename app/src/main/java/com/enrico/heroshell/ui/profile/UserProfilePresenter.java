package com.enrico.heroshell.ui.profile;

import android.os.Handler;

import com.enrico.heroshell.data.User;

/**
 * Created by enrico on 10/22/17.
 */

class UserProfilePresenter implements UserProfileContract.Presenter {
    UserProfileContract.View view;
    UserProfilePresenter(UserProfileContract.View view) {
        this.view = view;
    }
    public void getUser(final User user) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setNeedsProfileGrab(false);
                view.showProfile();
            }
        }, 1000l);
    }
}
