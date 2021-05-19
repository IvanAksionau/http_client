package com.ivan.client.http.requester.context

import org.apache.http.Header

interface IRequestHeadersContext {

    void putAuthHeader(Header header)

    Optional<Header> getAuthHeader()

    void removeAuthHeader()

    List<Header> getAllHeaders()
}
