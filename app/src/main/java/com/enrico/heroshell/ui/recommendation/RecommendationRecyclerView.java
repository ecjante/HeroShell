package com.enrico.heroshell.ui.recommendation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.enrico.heroshell.ui.base.BaseRecyclerView;

/**
 * Created by enrico on 10/21/17.
 */

public class RecommendationRecyclerView extends BaseRecyclerView {
    public RecommendationRecyclerView(Context context) {
        super(context);
    }

    public RecommendationRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecommendationRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void init() {
        super.init();
        getRecyclerView().setAdapter(new RecommendationRecyclerAdapter());
    }
}
