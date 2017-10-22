package com.enrico.heroshell.ui.chat;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enrico.heroshell.R;
import com.enrico.heroshell.Util.Utilities;

/**
 * Created by enrico on 10/22/17.
 */

public class MessageCell extends RelativeLayout {

    private TextView textView;
    private ChatRecyclerAdapter.ChatMessage message;

    public MessageCell(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.message_cell, this);
        textView = (TextView) findViewById(R.id.message);
    }

    public ChatRecyclerAdapter.ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatRecyclerAdapter.ChatMessage message) {
        this.message = message;
    }

    public void setup() {
        textView.setText(message.getMessage());
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(50f);
        if (message.getSender()) {
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
            lp.leftMargin = Utilities.dpToPx(getContext(), 96);
            lp.rightMargin = Utilities.dpToPx(getContext(), 8);
            gd.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            gd.setStroke(1, ContextCompat.getColor(getContext(), R.color.colorAccent));
        } else {
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
            lp.leftMargin = Utilities.dpToPx(getContext(), 8);
            lp.rightMargin = Utilities.dpToPx(getContext(), 96);
            gd.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryLight));
            gd.setStroke(1, ContextCompat.getColor(getContext(), R.color.colorPrimaryLight));
        }
        textView.setBackground(gd);
    }
}
