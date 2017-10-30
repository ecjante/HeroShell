package com.enrico.heroshell.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.data.HomeData;
import com.enrico.heroshell.ui.base.BaseRecyclerViewFragment;
import com.enrico.heroshell.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enrico on 10/19/17.
 */
public class HomeFragment extends BaseRecyclerViewFragment implements HomeContract.View {

    private HomeRecyclerAdapter adapter;
    private List<HomeData> homeData;
    private HomeContract.Presenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeData = new ArrayList<>();
        presenter = new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new HomeRecyclerAdapter(homeData);
        getRecyclerView().setAdapter(adapter);

        getHomeData();
    }

    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.updateToolbarTitle("Home");
        ContainerActivity.updateDrawerSelection(ContainerActivity.HOME_ID);
    }

    @Override
    public void showHome(List<HomeData> homeData) {
        adapter.addData(homeData);
        ContainerActivity.hideProgressBar();
    }

    private void getHomeData() {
        ContainerActivity.showProgressBar();
        presenter.getHome(getContext().getApplicationContext());
    }
}
