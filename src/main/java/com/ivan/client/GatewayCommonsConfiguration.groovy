package com.ivan.client

import com.ivan.client.http.client.HttpClientConfig
import com.ivan.client.http.client.IHttpClientFactory
import com.ivan.client.http.requester.IRequester
import com.ivan.client.http.requester.Requester
import com.ivan.client.http.requester.context.IRequestHeadersContext
import com.ivan.client.http.requester.log.DelegatingHttpLogger
import com.ivan.client.http.requester.log.HtmlHttpLogger
import com.ivan.client.http.requester.log.IHttpLogger
import com.ivan.client.http.requester.log.TextHttpLogger

import org.apache.http.impl.client.CloseableHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Configuration
@ComponentScan
@Lazy
class GatewayCommonsConfiguration {

    @Bean
    CloseableHttpClient httpClient(IHttpClientFactory httpClientFactory, HttpClientConfig httpClientConfig) {
        httpClientFactory.produceHttpClient(httpClientConfig)
    }

    @Bean
    DelegatingHttpLogger delegatingHttpLogger(
            @Value('${com.configuration/reportPortalEnabled}')
                    boolean reportPortalEnabled,
            TextHttpLogger textHttpLogger,
            HtmlHttpLogger htmlHttpLogger) {
        List<IHttpLogger> httpLoggers = []
        httpLoggers << textHttpLogger
        if (reportPortalEnabled) {
            httpLoggers << htmlHttpLogger
        }
        new DelegatingHttpLogger(httpLoggers)
    }

    @Bean
    IRequester requester(IRequestHeadersContext requestHeadersContext,
                         CloseableHttpClient httpClient,
                         DelegatingHttpLogger httpLogger) {
        new Requester(requestHeadersContext, httpClient, httpLogger)
    }
}
