package com.enrico.heroshell.ui.chat;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.R;
import com.enrico.heroshell.Util.Utilities;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements KeyboardVisibilityEventListener {
    private Unregistrar registrar;
    private RecyclerView recyclerView;
    private Button sendButton;
    private EditText messageBox;
    private ImageView moreButton;
    private RelativeLayout optionsView;
    ChatRecyclerAdapter adapter;
    ArrayList<ChatRecyclerAdapter.ChatMessage> messages;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messages = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainLayout = inflater.inflate(R.layout.fragment_chat, container, false);
        return mainLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ChatRecyclerAdapter(messages);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        optionsView = (RelativeLayout) view.findViewById(R.id.options_layout);

        sendButton = (Button) view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendMessage();
            }
        });
        sendButton.setClickable(false);

        messageBox = (EditText) view.findViewById(R.id.message_box);
        messageBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && isExpanded) {
                    ((RelativeLayout.LayoutParams) optionsView.getLayoutParams()).height = 0;
                    optionsView.requestLayout();
                    isExpanded = !isExpanded;
                }
            }
        });
        messageBox.addTextChangedListener(new TextWatcher() {
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

        moreButton = (ImageView) view.findViewById(R.id.more_button);
        moreButton.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoreButtonClick();
            }
        });
    }

    private void onSendMessage() {
        String message = messageBox.getText().toString();
        if (!message.isEmpty()) {
            adapter.addMessage(new ChatRecyclerAdapter.ChatMessage(message, true));
            messageBox.setText("");
            adapter.addMessage(new ChatRecyclerAdapter.ChatMessage(message, false));
            scrollToBottom();
        }
    }

    private boolean isExpanded = false;
    private void onMoreButtonClick() {
        ContainerActivity.hideKeyboard();
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
                scrollToBottom();
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
            messageBox.clearFocus();
        }
        scrollToBottom();
    }

    private void scrollToBottom() {
        if (adapter.getMessages().size() > 0) {
            recyclerView.smoothScrollToPosition(adapter.getMessages().size() - 1);
        }
    }
}
