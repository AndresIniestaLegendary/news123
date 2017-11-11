package com.example.a24811.news123;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by 24811 on 2017/11/10.
 */

public class NewsMainBean {
    int error_code;
    String message;
    DataBean data;
    public class DataBean{
        String index;
        String subject;
        private String content;
        String newscome;
        String gonggao;
        String shengao;
        String sheying;
        String visitcount;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
