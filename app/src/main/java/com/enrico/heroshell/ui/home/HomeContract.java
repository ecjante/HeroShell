package com.enrico.heroshell.ui.home;

import android.content.Context;

import com.enrico.heroshell.data.HomeData;

import java.util.List;

/**
 * Created by enrico on 10/22/17.
 */

interface HomeContract {
    interface View {
        void showHome(List<HomeData> homeData);
    }
    interface Presenter {
        void getHome(Context context);
    }
}
