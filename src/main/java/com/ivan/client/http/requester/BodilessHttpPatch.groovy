package com.ivan.client.http.requester

import org.apache.http.client.methods.HttpRequestBase

class BodilessHttpPatch extends HttpRequestBase {
    public static final String METHOD_NAME = 'PATCH'

    BodilessHttpPatch() {
    }

    BodilessHttpPatch(final URI uri) {
        setURI(uri)
    }

    /**
     * @throws IllegalArgumentException if the uri is invalid.
     */
    BodilessHttpPatch(final String uri) {
        setURI(URI.create(uri))
    }

    @SuppressWarnings('GetterMethodCouldBeProperty')
    @Override
    String getMethod() {
        METHOD_NAME
    }
}
