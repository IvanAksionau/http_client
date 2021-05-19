package com.ivan.client.http.requester.log

import com.ivan.client.http.requester.HttpResponse
import groovy.util.logging.Slf4j
import org.apache.http.Header
import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase
import org.apache.http.client.methods.HttpUriRequest
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Slf4j
@Lazy
@Component
class TextHttpLogger implements IHttpLogger {

    @Override
    void logRequest(HttpUriRequest request) {
        StringBuilder requestLog = new StringBuilder("Sending ${request.method} request to url: ${request.URI}.")
        appendHeaders(requestLog, 'Request headers:', request.allHeaders)
        if (request in HttpEntityEnclosingRequestBase) {
            appendBody(requestLog, request.entity)
        }
        log.info(requestLog.toString())
    }

    @Override
    void logResponse(HttpResponse response) {
        StringBuilder responseLog = new StringBuilder('Received response [')
                .append(response.statusLine)
                .append('] from: ')
                .append(response.from)
        appendHeaders(responseLog, 'Response headers:', response.responseHeaders)
        appendBody(responseLog, response)
        log.info(responseLog.toString())
    }

    private static void appendHeaders(StringBuilder builder, String prompt, Header... headers) {
        builder.append(System.lineSeparator()).append(prompt)
        headers.each {
            builder.append(System.lineSeparator()).append("- ${it.name} : ${it.value}")
        }
    }

    private static void appendBody(StringBuilder builder, HttpEntity entity) {
        HttpLogUtils.convertToString(entity).ifPresent { appendBody(builder, it) }
    }

    private static void appendBody(StringBuilder builder, HttpResponse response) {
        if (HttpLogUtils.isPrintable(response)) {
            appendBody(builder, response.responseBodyAsString)
        }
    }

    private static void appendBody(StringBuilder builder, String body) {
        builder.append(System.lineSeparator())
                .append('Body:')
                .append(System.lineSeparator())
                .append(body)
    }
}
