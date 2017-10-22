package com.enrico.heroshell.ui.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseRecyclerViewFragment extends Fragment {

    private BaseRecyclerView mBaseRecyclerView;

    public BaseRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBaseRecyclerView = new BaseRecyclerView(getContext());
        return mBaseRecyclerView;
    }

    public RecyclerView getRecyclerView() {
        return mBaseRecyclerView.getRecyclerView();
    }
}
