package com.ivan.client.http.requester

import org.apache.http.client.methods.HttpRequestBase

class BodilessHttpPut extends HttpRequestBase {

    public final static String METHOD_NAME = 'PUT'

    BodilessHttpPut() { }

    BodilessHttpPut(final URI uri) {
        setURI(uri)
    }

    /**
     * @throws IllegalArgumentException if the uri is invalid.
     */
    BodilessHttpPut(final String uri) {
        setURI(URI.create(uri))
    }

    @SuppressWarnings('GetterMethodCouldBeProperty')
    @Override
    String getMethod() {
        METHOD_NAME
    }
}
