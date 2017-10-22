package com.enrico.heroshell.ui.home;

import android.content.Context;
import android.os.Handler;

import com.enrico.heroshell.data.HomeData;

import java.util.ArrayList;

/**
 * Created by enrico on 10/22/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getHome(Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int length = 20;
                ArrayList<HomeData> homeData = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    homeData.add(new HomeData("primary " + (i + 1), "secondary " + (i + 1)));
                }
                view.showHome(homeData);
            }
        }, 5000L);
    }
}
