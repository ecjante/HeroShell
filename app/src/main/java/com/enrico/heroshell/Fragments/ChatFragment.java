package com.enrico.heroshell.Fragments;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.Adapters.ChatRecyclerAdapter;
import com.enrico.heroshell.R;
import com.enrico.heroshell.Util.Utilities;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements KeyboardVisibilityEventListener {
    private Unregistrar registrar;
    View mainLayout;
    ChatRecyclerAdapter adapter;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainLayout = inflater.inflate(R.layout.fragment_chat, container, false);

        adapter = new ChatRecyclerAdapter();
        RecyclerView recyclerView = (RecyclerView) mainLayout.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        final TextView sendButton = (TextView) mainLayout.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendMessage();
            }
        });
        sendButton.setClickable(false);

        EditText editText = (EditText) mainLayout.findViewById(R.id.message_box);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && isExpanded) {
                    RelativeLayout optionsView = (RelativeLayout) mainLayout.findViewById(R.id.options_layout);
                    ((RelativeLayout.LayoutParams) optionsView.getLayoutParams()).height = 0;
                    optionsView.requestLayout();
                    isExpanded = !isExpanded;
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    sendButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                    sendButton.setClickable(true);
                } else {
                    sendButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary_light));
                    sendButton.setClickable(false);
                }
            }
        });

        ImageView moreButton = (ImageView) mainLayout.findViewById(R.id.more_button);
        moreButton.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoreButtonClick();
            }
        });
        return mainLayout;
    }

    private void onSendMessage() {
        EditText messageBox = (EditText) mainLayout.findViewById(R.id.message_box);
        String message = messageBox.getText().toString();
        if (!message.isEmpty()) {
            adapter.addMessage(new ChatRecyclerAdapter.ChatMessage(message, true));
            messageBox.setText("");
            adapter.addMessage(new ChatRecyclerAdapter.ChatMessage(message, false));
            RecyclerView recyclerView = (RecyclerView) mainLayout.findViewById(R.id.recycler_view);
            recyclerView.scrollToPosition(adapter.getMessages().size() - 1);
        }
    }

    private boolean isExpanded = false;
    private void onMoreButtonClick() {
        ContainerActivity.hideKeyboard();
        final RelativeLayout optionsView = (RelativeLayout) mainLayout.findViewById(R.id.options_layout);
        ValueAnimator anim;
        if (isExpanded) {
            anim = ValueAnimator.ofInt(Utilities.dpToPx(getActivity(), 192), 0);
        } else {
            anim = ValueAnimator.ofInt(0, Utilities.dpToPx(getActivity(), 192));
        }
        anim.setDuration(250L);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((RelativeLayout.LayoutParams) optionsView.getLayoutParams()).height = (int) animation.getAnimatedValue();
                optionsView.requestLayout();
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                isExpanded = !isExpanded;
                RecyclerView recyclerView = (RecyclerView) mainLayout.findViewById(R.id.recycler_view);
                recyclerView.scrollToPosition(adapter.getMessages().size() - 1);
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        anim.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        registrar = KeyboardVisibilityEvent.registerEventListener(getActivity(), this);
        ContainerActivity.updateToolbarTitle("Chat");
        ContainerActivity.updateDrawerSelection(ContainerActivity.CHAT_ID);
    }

    @Override
    public void onStop() {
        super.onStop();
        registrar.unregister();
        registrar = null;
        ContainerActivity.hideKeyboard();
    }

    @Override
    public void onVisibilityChanged(boolean isOpen) {
        if (!isOpen) {
            EditText editText = (EditText) mainLayout.findViewById(R.id.message_box);
            editText.clearFocus();
        }
        RecyclerView recyclerView = (RecyclerView) mainLayout.findViewById(R.id.recycler_view);
        recyclerView.scrollToPosition(adapter.getMessages().size() - 1);
    }
}
