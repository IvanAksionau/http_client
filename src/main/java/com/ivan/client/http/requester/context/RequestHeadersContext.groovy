package com.ivan.client.http.requester.context

import com.ivan.client.context.IContext
import org.apache.http.Header
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Lazy
@Component
class RequestHeadersContext implements IRequestHeadersContext {

    private static final String AUTH_HEADER_KEY = 'auth_header_key'

    private final IContext context

    @Autowired
    RequestHeadersContext(IContext context) {
        this.context = context
    }

    @Override
    void putAuthHeader(Header header) {
        context.put(AUTH_HEADER_KEY, header)
    }

    @Override
    Optional<Header> getAuthHeader() {
        Optional.ofNullable(context.get(AUTH_HEADER_KEY, Header))
    }

    @Override
    void removeAuthHeader() {
        context.remove(AUTH_HEADER_KEY)
    }

    @Override
    List<Header> getAllHeaders() {
        List<Header> headers = []
        authHeader.ifPresent { headers << it }
        headers
    }
}
