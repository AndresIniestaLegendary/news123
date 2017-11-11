package com.example.a24811.news123;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    NewsListBean newsListBean;
    NewsAdapter adapter;
    Handler handler;
    RecyclerView recyclerView;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(){
            /**
             * Subclasses must implement this to receive messages.
             *
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 2){
                    adapter = new NewsAdapter(getApplicationContext(),newsListBean);
                    recyclerView.setAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://open.twtstudio.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Api api = retrofit.create(Api.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    retrofit2.Response<NewsListBean> response = api.getNewsList(1,1).execute();
                    newsListBean = response.body();
                    Log.d("hfudh", "dnckhv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        }).start();
    }
}
