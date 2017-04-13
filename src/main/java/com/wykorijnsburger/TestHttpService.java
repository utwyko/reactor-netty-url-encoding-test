package com.wykorijnsburger;

import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.client.HttpClient;
import reactor.ipc.netty.http.client.HttpClientException;
import reactor.ipc.netty.http.client.HttpClientResponse;
import reactor.ipc.netty.resources.PoolResources;

public class TestHttpService {

    public static HttpResponseStatus testHttpCallWithUrlEncoding(final String url) {
        final HttpClient httpClient = HttpClient.create(ops ->
            ops.connect("jsonplaceholder.typicode.com", 80)
                .poolResources(PoolResources.elastic("TYPICODE")));

        return httpClient.get(url)
            .map(HttpClientResponse::status)
            .otherwise(HttpClientException.class, e -> {
                if (e.status().equals(HttpResponseStatus.NOT_FOUND)) {
                    return Mono.just(e.status());
                }
                return Mono.error(e);
            })
            .block();
    }
}
