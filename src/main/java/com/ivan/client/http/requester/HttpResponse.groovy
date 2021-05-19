package com.ivan.client.http.requester

import groovy.transform.ToString
import org.apache.commons.lang3.ArrayUtils
import org.apache.http.Header
import org.apache.http.StatusLine

import java.nio.charset.StandardCharsets

@ToString(excludes = 'responseBody')
class HttpResponse {

    private final Closure modelSupplier

    URI from
    String method
    StatusLine statusLine
    byte[] responseBody
    Header[] responseHeaders

    HttpResponse(Closure modelSupplier) {
        this.modelSupplier = modelSupplier
    }

    HttpResponse verifyStatusCode(int ... validCodes) {
        int statusCode = statusLine.statusCode
        if (validCodes.contains(statusCode)) {
            return this
        }
        throw new HttpResponseException(
                "Response has unexpected status code: ${statusCode}. " +
                        "Expected one of: ${validCodes.join(', ')}")
    }

    Optional<Header> getHeaderByName(String headerName) {
        Optional.ofNullable(responseHeaders.find { it.name == headerName })
    }

    void setResponseBody(byte[] responseBody) {
        this.responseBody = ArrayUtils.clone(responseBody)
    }

    byte[] getResponseBody() {
        ArrayUtils.clone(responseBody)
    }

    String getResponseBodyAsString() {
        responseBody == null ?: new String(responseBody, StandardCharsets.UTF_8.name())
    }

    public <T> T getResponseBodyAs(Class<T> type) {
        modelSupplier(responseBody, type)
    }

    Header[] getResponseHeaders() {
        ArrayUtils.clone(responseHeaders)
    }

    void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = ArrayUtils.clone(responseHeaders)
    }

    int getStatusCode() {
        statusLine.statusCode
    }
}
