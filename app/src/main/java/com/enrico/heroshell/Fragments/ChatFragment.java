package com.enrico.heroshell.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.Adapters.ChatRecyclerAdapter;
import com.enrico.heroshell.R;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

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

        TextView sendButton = (TextView) mainLayout.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendMessage();
            }
        });
        return mainLayout;
    }

    public void onSendMessage() {
        EditText messageBox = (EditText) mainLayout.findViewById(R.id.message_box);
        String message = messageBox.getText().toString();
        adapter.addMessage(new ChatRecyclerAdapter.ChatMessage(message, true));
        messageBox.setText("");
        adapter.addMessage(new ChatRecyclerAdapter.ChatMessage(message, false));
        RecyclerView recyclerView = (RecyclerView) mainLayout.findViewById(R.id.recycler_view);
        recyclerView.scrollToPosition(adapter.getMessages().size() - 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.updateToolbarTitle("Chat");
        ContainerActivity.updateDrawerSelection(ContainerActivity.CHAT_ID);
    }

    @Override
    public void onStop() {
        super.onStop();
        ContainerActivity.hideKeyboard();
    }
}
