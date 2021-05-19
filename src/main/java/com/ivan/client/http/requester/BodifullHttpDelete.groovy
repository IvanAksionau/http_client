package com.ivan.client.http.requester

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase

class BodifullHttpDelete extends HttpEntityEnclosingRequestBase {

    public final static String METHOD_NAME = 'DELETE'

    BodifullHttpDelete() { }

    BodifullHttpDelete(final URI uri) {
        setURI(uri)
    }

    /**
     * @throws IllegalArgumentException if the uri is invalid.
     */
    BodifullHttpDelete(final String uri) {
        setURI(URI.create(uri))
    }

    @SuppressWarnings('GetterMethodCouldBeProperty')
    @Override
    String getMethod() {
        METHOD_NAME
    }
}
