package com.enrico.heroshell.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFollowingFragment extends BaseFragment {


    public UserFollowingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_following, container, false);
    }

    @Override
    public void didAppear() {
        ContainerActivity.updateToolbarTitle("Following");
    }

    @Override
    public void didDisappear() {

    }
}
