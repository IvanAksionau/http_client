package com.ivan.client.http.requester.log

import com.ivan.client.http.requester.HttpResponse
import org.apache.http.client.methods.HttpUriRequest

interface IHttpLogger {

    void logRequest(HttpUriRequest request)

    void logResponse(HttpResponse response)
}
