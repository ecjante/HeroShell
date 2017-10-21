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
import com.enrico.heroshell.Adapters.UserFollowingRecyclerAdapter;
import com.enrico.heroshell.Models.User;
import com.enrico.heroshell.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFollowingFragment extends Fragment {


    public UserFollowingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_following, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<User> users = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            users.add(new User());
        }
        recyclerView.setAdapter(new UserFollowingRecyclerAdapter(users));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.updateToolbarTitle("Following");
    }
}
