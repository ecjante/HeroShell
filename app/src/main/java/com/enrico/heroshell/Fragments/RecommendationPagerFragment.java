package com.enrico.heroshell.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.Adapters.RecommendationPagerAdapter;
import com.enrico.heroshell.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendationPagerFragment extends Fragment {

    private RecommendationPagerAdapter adapter;
    private View mainLayout;
    private int selectedTab = 0;

    public RecommendationPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainLayout = inflater.inflate(R.layout.fragment_recommendation_pager, container, false);
        ViewPager viewPager = (ViewPager) mainLayout.findViewById(R.id.view_pager);
        SmartTabLayout tabs = (SmartTabLayout) mainLayout.findViewById(R.id.tabs);

        int[][] states = new int[2][1];

        adapter = new RecommendationPagerAdapter();
        viewPager.setAdapter(adapter);
        tabs.setViewPager(viewPager);

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageScrollStateChanged(int state) {}

            @Override
            public void onPageSelected(int position) {
                selectedTab = position;
                changeToolbarTitle();
            }
        });

        return mainLayout;
    }

    public void changeToolbarTitle() {
        switch (selectedTab) {
            case 0:
                ContainerActivity.updateToolbarTitle("Recommendations");
                break;
            case 1:
                ContainerActivity.updateToolbarTitle("Herograms");
                break;
            case 2:
                ContainerActivity.updateToolbarTitle("Recommended To");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        changeToolbarTitle();
    }
}
