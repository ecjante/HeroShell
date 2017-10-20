package com.enrico.heroshell.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enrico.heroshell.R;

/**
 * Created by enrico on 10/19/17.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    HomeData[] homeData;
    public class HomeData {
        String primaryString;
        String secondaryString;

        public HomeData(String primary, String secondary) {
            this.primaryString = primary;
            this.secondaryString = secondary;
        }

        public String getPrimaryString() {
            return primaryString;
        }

        public void setPrimaryString(String primaryString) {
            this.primaryString = primaryString;
        }

        public String getSecondaryString() {
            return secondaryString;
        }

        public void setSecondaryString(String secondaryString) {
            this.secondaryString = secondaryString;
        }
    }
    public HomeRecyclerAdapter() {
        int length = 20;
        homeData = new HomeData[length];
        for (int i = 0; i < length; i++) {
            homeData[i] = new HomeData("primary " + (i + 1), "secondary " + (i + 1));
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View itemView = holder.itemView;
        HomeData data = homeData[position];
        String index = String.valueOf(position + 1);
        ((TextView) itemView.findViewById(R.id.home_cell_index_text)).setText(String.valueOf(index));
        ((TextView) itemView.findViewById(R.id.home_cell_primary_text)).setText(data.getPrimaryString());
        ((TextView) itemView.findViewById(R.id.home_cell_secondary_text)).setText(data.getSecondaryString());
    }

    @Override
    public int getItemCount() {
        return homeData.length;
    }
}
