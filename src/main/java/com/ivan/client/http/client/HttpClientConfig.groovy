package com.ivan.client.http.client

import com.ivan.client.ConfigurationError

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Lazy
@Component
class HttpClientConfig {

    @Value('${http.client.HttpClientConfig/contentCompressionDisabled}')
    boolean contentCompressionDisabled

    @SuppressWarnings('LineLength')
    @Value('${http.client.HttpClientConfig/sslHostnameVerificationDisabled}')
    boolean sslHostnameVerificationDisabled

    @Value('${http.client.HttpClientConfig/autoRedirectDisabled}')
    boolean autoRedirectDisabled

    @Value('${http.client.HttpClientConfig/cookiePolicy}')
    String cookiePolicy

    @Value('${http.client.HttpClientConfig/maxConnections}')
    int maxConnections

    @Value('${http.client.HttpClientConfig/maxConnectionsPerRoute}')
    int maxConnectionsPerRoute

    HttpClientConfig() {
    }

    HttpClientConfig(HttpClientConfig original) {
        this.contentCompressionDisabled = original.contentCompressionDisabled
        this.sslHostnameVerificationDisabled = original.sslHostnameVerificationDisabled
        this.autoRedirectDisabled = original.autoRedirectDisabled
        this.cookiePolicy = original.cookiePolicy
        this.maxConnections = original.maxConnections
        this.maxConnectionsPerRoute = original.maxConnectionsPerRoute
    }

    boolean hasCookiePolicy() {
        cookiePolicy != null && !cookiePolicy.empty
    }

    @PostConstruct
    void validateMultithreadingConfig() {
        if (maxConnections < maxConnectionsPerRoute) {
            throw new ConfigurationError("Invalid http client configuration has been found:\
 maxConnectionsPerRoute (current value ${maxConnectionsPerRoute}) shouldn't be greater than\
 maxConnections (current value ${maxConnections}).")
        }
    }
}
