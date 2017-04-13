package com.wykorijnsburger;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHttpServiceTest {

    @Test
    public void shouldHandleUrlsWithEncodedPercent() {
        assertThat(TestHttpService.testHttpCallWithUrlEncoding("/posts/5%255")).isEqualTo(HttpResponseStatus.NOT_FOUND);
    }

    @Test
    public void shouldHandleUrlsWithEncodedSpace() {
        assertThat(TestHttpService.testHttpCallWithUrlEncoding("/posts/5%205")).isEqualTo(HttpResponseStatus.NOT_FOUND);
    }

    @Test
    public void shouldHandleUrlWithoutEncodedCharacters() {
        assertThat(TestHttpService.testHttpCallWithUrlEncoding("/posts/1")).isEqualTo(HttpResponseStatus.OK);
    }
}