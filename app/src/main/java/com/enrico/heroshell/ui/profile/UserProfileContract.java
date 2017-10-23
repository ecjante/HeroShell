package com.enrico.heroshell.ui.profile;

import com.enrico.heroshell.data.User;

/**
 * Created by enrico on 10/22/17.
 */

interface UserProfileContract {
    interface View {
        void showProfile();
    }
    interface Presenter {
        void getUser(User user);
    }
}
