package com.ivan.client.http.client

import org.apache.http.client.config.RequestConfig
import org.apache.http.config.Registry
import org.apache.http.config.RegistryBuilder
import org.apache.http.conn.socket.ConnectionSocketFactory
import org.apache.http.conn.socket.PlainConnectionSocketFactory
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.TrustAllStrategy
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.ssl.SSLContexts
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext

@Lazy
@Component
class HttpClientFactory implements IHttpClientFactory {

    @Override
    CloseableHttpClient produceHttpClient(HttpClientConfig config) {
        HttpClientBuilder builder = HttpClientBuilder.create()
        PoolingHttpClientConnectionManager connectionManager

        if (config.contentCompressionDisabled) {
            builder.disableContentCompression()
        }
        if (config.sslHostnameVerificationDisabled) {
            connectionManager = connManagerWithDisabledSslHostnameVerification(builder)
        } else {
            connectionManager = new PoolingHttpClientConnectionManager()
        }
        if (config.autoRedirectDisabled) {
            builder.disableRedirectHandling()
        }
        if (config.hasCookiePolicy()) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setCookieSpec(config.cookiePolicy)
                    .build()
            builder.setDefaultRequestConfig(requestConfig)
        }

        connectionManager.maxTotal = config.maxConnections
        connectionManager.defaultMaxPerRoute = config.maxConnectionsPerRoute
        builder.connectionManager = connectionManager

        builder.build()
    }

    private static PoolingHttpClientConnectionManager connManagerWithDisabledSslHostnameVerification(
            final HttpClientBuilder builder) {
        String protocolVersion = 'TLSv1.2'
        SSLContext sslContext = SSLContexts.custom()
                .setProtocol(protocolVersion)
                .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                .build()

        builder.setSSLContext(sslContext)
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                [protocolVersion] as String[],
                null,
                hostnameVerifier)

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register('http', PlainConnectionSocketFactory.socketFactory)
                .register('https', sslSocketFactory)
                .build() as Registry<ConnectionSocketFactory>
        new PoolingHttpClientConnectionManager(socketFactoryRegistry)
    }
}
