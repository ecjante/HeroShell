package com.enrico.heroshell.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.Adapters.HomeRecyclerAdapter;
import com.enrico.heroshell.R;

/**
 * Created by enrico on 10/19/17.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new HomeRecyclerAdapter());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.updateToolbarTitle("Home");
        ContainerActivity.updateDrawerSelection(ContainerActivity.HOME_ID);
    }
}
