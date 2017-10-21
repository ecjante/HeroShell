package com.enrico.heroshell.Adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.enrico.heroshell.R;
import com.enrico.heroshell.Views.RecommendationRecyclerView;

/**
 * Created by enrico on 10/21/17.
 */

public class RecommendationPagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Recommendations";
            case 1: return "Herograms";
            case 2: return "Recommended To";
            default: return "";
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecommendationRecyclerView v = new RecommendationRecyclerView(container.getContext());
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
