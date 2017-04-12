package com.wykorijnsburger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.ipc.netty.http.client.HttpClient;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        final HttpClient httpClient = HttpClient.create("jsonplaceholder.typicode.com/");

        final String block = httpClient.get("/posts/5%255")
            .then(response -> response.receive().aggregate().asString())
            .block();

        return block;
    }
}
