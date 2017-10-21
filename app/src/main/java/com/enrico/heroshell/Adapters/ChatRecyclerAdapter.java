package com.enrico.heroshell.Adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.R;
import com.enrico.heroshell.Util.Utilities;

import java.util.ArrayList;

/**
 * Created by enrico on 10/20/17.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static class ChatMessage {
        private String message;
        private Boolean isSender;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Boolean getSender() {
            return isSender;
        }

        public void setSender(Boolean sender) {
            isSender = sender;
        }

        public ChatMessage(String message, Boolean isSender) {
            this.message = message;
            this.isSender = isSender;
        }
    }
    ArrayList<ChatMessage> messages;

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public ChatRecyclerAdapter() {
        messages = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View view = holder.itemView;
        ChatMessage message = messages.get(position);
        TextView textView = (TextView) holder.itemView.findViewById(R.id.message);
        textView.setText(message.message);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(25f);
        if (message.isSender) {
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
            gd.setColor(ContextCompat.getColor(ContainerActivity.getContext(), R.color.colorAccent));
            gd.setStroke(1, ContextCompat.getColor(ContainerActivity.getContext(), R.color.colorAccent));
        } else {
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
            gd.setColor(ContextCompat.getColor(ContainerActivity.getContext(), R.color.colorPrimaryLight));
            gd.setStroke(1, ContextCompat.getColor(ContainerActivity.getContext(), R.color.colorPrimaryLight));
        }
        textView.setBackground(gd);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(ChatMessage message) {
        messages.add(message);
        notifyDataSetChanged();
    }
}
