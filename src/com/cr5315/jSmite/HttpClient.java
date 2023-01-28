package com.cr5315.jSmite;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpClient {
    private static OkHttpClient okHttpClient;

    private HttpClient() {
        okHttpClient = getInstance();
    }

    public static synchronized OkHttpClient getInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }

        return okHttpClient;
    }

    public static String fetch(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = getInstance().newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return null;
        }
    }
}
