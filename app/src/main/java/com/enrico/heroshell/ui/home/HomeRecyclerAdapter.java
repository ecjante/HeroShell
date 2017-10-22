package com.enrico.heroshell.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enrico.heroshell.data.HomeData;
import com.enrico.heroshell.ui.base.ViewHolder;
import com.enrico.heroshell.R;

import java.util.List;

/**
 * Created by enrico on 10/19/17.
 */

class HomeRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<HomeData> homeData;
    HomeRecyclerAdapter(List<HomeData> homeData) {
        this.homeData = homeData;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new HomeCell());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.itemView instanceof HomeCell) {
            HomeCell itemView = (HomeCell) holder.itemView;
            HomeData data = homeData.get(position);
            itemView.setPrimaryString(data.getPrimaryString());
            itemView.setSecondaryString(data.getSecondaryString());
            itemView.setIndex(position + 1);
            itemView.setup();
        }
    }

    @Override
    public int getItemCount() {
        return homeData.size();
    }

    public void clear() {
        int size = getItemCount();
        this.homeData.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addData(List<HomeData> homeData) {
        int prevSize = getItemCount();
        this.homeData.addAll(homeData);
        notifyItemRangeInserted(prevSize, homeData.size());
    }
}
