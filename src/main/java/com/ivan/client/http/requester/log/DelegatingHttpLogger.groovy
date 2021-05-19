package com.ivan.client.http.requester.log

import com.ivan.client.http.requester.HttpResponse
import org.apache.http.client.methods.HttpUriRequest

import static java.util.Collections.unmodifiableList

class DelegatingHttpLogger implements IHttpLogger {

    private final List<IHttpLogger> loggers

    DelegatingHttpLogger(List<IHttpLogger> loggers) {
        this.loggers = unmodifiableList(loggers)
    }

    @Override
    void logRequest(HttpUriRequest request) {
        loggers.each { it.logRequest(request) }
    }

    @Override
    void logResponse(HttpResponse response) {
        loggers.each { it.logResponse(response) }
    }
}
