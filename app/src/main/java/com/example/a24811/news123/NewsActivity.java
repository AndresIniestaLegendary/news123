package com.example.a24811.news123;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {
    NewsMainBean newsMainBean;
    WebView webView;
    Retrofit retrofit;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Bundle bundle = getIntent().getExtras();
        final String index = bundle.getString("text");
        webView = (WebView) findViewById(R.id.webview_item);
        handler = new Handler(){
            /**
             * Subclasses must implement this to receive messages.
             *
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 2){
                    webView.loadData(newsMainBean.data.getContent(),"text/html;charset=utf-8" ,null);
                }
                super.handleMessage(msg);
            }
        };
        retrofit = new Retrofit.Builder()
                .baseUrl("http://open.twtstudio.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Api2 api2 = retrofit.create(Api2.class);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Response<NewsMainBean> response = api2.getNewMain(index).execute();
                    newsMainBean = response.body();
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
