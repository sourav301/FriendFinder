package com.example.sourav.twitterapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sourav.twitterapp.model.NetworkCalls;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    TextView helloTextView;
    Button myButton;
    String URL = "http://192.168.1.37:8080/FriendFinder/Location";
    NetworkCalls networkCalls;
    ListView chatListView;
    List<String> chats;
    EditText messageText;
    ArrayAdapter<String> arrayAdapter;
    FetchReply fetchReply;
    final String TAG = "Chat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloTextView=(TextView)findViewById(R.id.helloWorld);
        myButton = (Button)findViewById(R.id.myButton);
        chatListView = (ListView)findViewById(R.id.chatList);
        messageText = (EditText)findViewById(R.id.messageText);
        networkCalls = new NetworkCalls();
        chats = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,chats);
        chatListView.setAdapter(arrayAdapter);

        chatListView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public void click(View v){
        chats.add(messageText.getText().toString());
        messageText.setText("");
        chatListView.invalidateViews();
        chatListView.setSelection(arrayAdapter.getCount() - 1);
        fetchReply = new FetchReply();
        fetchReply.execute("URL");
    }

    class FetchReply extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG,"Background");
            return networkCalls.getData(URL);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG,"Post execute");
            super.onPostExecute(s);

            chats.add(s);
            chatListView.invalidateViews();
            chatListView.setSelection(arrayAdapter.getCount() - 1);

        }
    }

}
