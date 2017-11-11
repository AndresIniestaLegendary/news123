package com.example.a24811.news123;

import java.util.List;

/**
 * Created by 24811 on 2017/11/7.
 */

public class NewsListBean {
    private int error_code;
    private String message;
    public List<DataBean> data;

    public static class DataBean {
        public String index;
        public String subject;
        public String pic;
        public String visitcount;
        public String summary;
    }
}
