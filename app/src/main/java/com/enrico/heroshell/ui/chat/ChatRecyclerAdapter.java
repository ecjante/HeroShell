package com.enrico.heroshell.ui.chat;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.enrico.heroshell.ui.base.ViewHolder;

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

    public ChatRecyclerAdapter(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageCell v = new MessageCell(parent.getContext());
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        if (holder.itemView instanceof MessageCell) {
            ((MessageCell) holder.itemView).setMessage(message);
            ((MessageCell) holder.itemView).setup();
        }
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
