package chatbot.example.com.chatbotapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class ChatScreen extends AppCompatActivity implements ViewInterface, View.OnClickListener {

    Toolbar toolbar;
    RecyclerView chatList;
    AppCompatEditText userMessage;
    Button sendButton;
    ChatPresenter presenter;
    ArrayList<ResponseModel> data;
    MessageAdapter adapter;
    TextView noMessage;
    String ARG_DATA = "data";
    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        initializeViews();
        presenter = new ChatPresenter(this);
        if (savedInstanceState != null) {
            String savedDataString = savedInstanceState.getString(ARG_DATA);
            data = new Gson().fromJson(savedDataString, new TypeToken<ArrayList<ResponseModel>>() {
            }.getType());
            if (data != null && data.size() > 0) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void initializeViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        chatList = (RecyclerView) findViewById(R.id.message_list);
        userMessage = (AppCompatEditText) findViewById(R.id.send_txt);
        sendButton = (Button) findViewById(R.id.send_btn);
        adapter = new MessageAdapter();
        chatList.setLayoutManager(new LinearLayoutManager(this));
        chatList.setAdapter(adapter);
        noMessage = (TextView) findViewById(R.id.no_msg);
        backButton = (ImageView) findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

        if (data == null || data.size() == 0) {
            chatList.setVisibility(View.GONE);
            noMessage.setVisibility(View.VISIBLE);
        } else {
            chatList.setVisibility(View.VISIBLE);
            noMessage.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String savedString = new Gson().toJson(data);
        outState.putString(ARG_DATA, savedString);

    }

    @Override
    public void onAPISuccessResponse(ResponseModel data) {
        if (data != null) {
            if (this.data == null)
                this.data = new ArrayList<>();
            this.data.add(data);
            noMessage.setVisibility(View.GONE);
            chatList.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAPIErrorResponse(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.send_btn:

                if (userMessage != null && userMessage.getText().toString() != null) {
                    if (userMessage.getText().toString().isEmpty()) {
                        Toast.makeText(ChatScreen.this, R.string.empty_message, Toast.LENGTH_LONG).show();
                    } else {
                        addMessageToAdapter(userMessage.getText().toString());
                        presenter.chatWithBot(userMessage.getText().toString());
                    }
                } else {
                    Toast.makeText(ChatScreen.this, R.string.something_went_wrong, Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.back_button:
                finish();
        }
    }

    private void addMessageToAdapter(String message) {
        ResponseModel userMessageModel = new ResponseModel();
        userMessageModel.setUser(true);
        Message dummyMessage = new Message();
        dummyMessage.setMessage(message);
        userMessageModel.setMessage(dummyMessage);
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(userMessageModel);
        noMessage.setVisibility(View.GONE);
        chatList.setVisibility(View.VISIBLE);
        userMessage.getText().clear();
        adapter.notifyDataSetChanged();


    }

    public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ChatScreen.this)
                    .inflate(R.layout.message_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (data.get(position).isUser()) {
                holder.botMessage.setVisibility(View.GONE);
                holder.userMessage.setVisibility(View.VISIBLE);
                holder.userMessage.setText(data.get(position).getMessage().getMessage());
            } else {
                holder.botMessage.setVisibility(View.VISIBLE);
                holder.userMessage.setVisibility(View.GONE);
                if (data.get(position).getMessage() != null) {
                    holder.botMessage.setText(data.get(position).getMessage().getMessage());
                } else {
                    holder.botMessage.setText(data.get(position).getErrorMessage());
                    holder.botMessage.setTextColor(getResources().getColor(android.R.color.darker_gray));
                }
            }
        }

        @Override
        public int getItemCount() {
            return data != null && data.size() > 0 ? data.size() : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView userMessage, botMessage;

            public ViewHolder(View itemView) {
                super(itemView);
                userMessage = (TextView) itemView.findViewById(R.id.user_message_item);
                botMessage = (TextView) itemView.findViewById(R.id.bot_message_item);
            }

        }


    }
}
