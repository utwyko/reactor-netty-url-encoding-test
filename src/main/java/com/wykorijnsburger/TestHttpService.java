package com.wykorijnsburger;

import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.ipc.netty.http.client.HttpClient;

import java.time.Duration;

public class TestHttpService {

    public static HttpResponseStatus testHttpCallWithUrlEncoding(final String url) {
        final HttpClient httpClient = HttpClient.create("jsonplaceholder.typicode.com");

        return httpClient.get(url)
            .block(Duration.ofMillis(100))
            .status();
    }
}
