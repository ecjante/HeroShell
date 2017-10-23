package com.enrico.heroshell;

import android.app.Application;

import com.enrico.heroshell.data.User;

/**
 * Created by enrico on 10/22/17.
 */

public class HeroApplication extends Application {

    private User loggedInUser;
    private static HeroApplication self;

    public HeroApplication() {
        loggedInUser = new User("ecjpiano");
        self = this;
    }

    public static boolean isLoggedInUser(String username) {
        return username.equals(self.loggedInUser.getUsername());
    }
}
