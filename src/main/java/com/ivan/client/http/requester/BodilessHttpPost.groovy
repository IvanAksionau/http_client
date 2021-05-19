package com.ivan.client.http.requester

import org.apache.http.client.methods.HttpRequestBase

class BodilessHttpPost extends HttpRequestBase {

    public final static String METHOD_NAME = 'POST'

    BodilessHttpPost() { }

    BodilessHttpPost(final URI uri) {
        setURI(uri)
    }

    /**
     * @throws IllegalArgumentException if the uri is invalid.
     */
    BodilessHttpPost(final String uri) {
        setURI(URI.create(uri))
    }

    @SuppressWarnings('GetterMethodCouldBeProperty')
    @Override
    String getMethod() {
        METHOD_NAME
    }
}
