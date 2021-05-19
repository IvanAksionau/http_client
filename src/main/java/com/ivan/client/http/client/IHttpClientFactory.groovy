package com.ivan.client.http.client

import org.apache.http.impl.client.CloseableHttpClient

interface IHttpClientFactory {

    CloseableHttpClient produceHttpClient(HttpClientConfig config)
}
