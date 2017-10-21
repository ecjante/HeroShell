package com.enrico.heroshell.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enrico.heroshell.Adapters.RecommendationRecyclerAdapter;
import com.enrico.heroshell.R;
import com.enrico.heroshell.Views.BaseRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseRecyclerViewFragment extends Fragment {


    public BaseRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BaseRecyclerView view = new BaseRecyclerView(getContext());
        return view;
    }

}
