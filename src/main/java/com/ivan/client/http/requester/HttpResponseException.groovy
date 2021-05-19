package com.ivan.client.http.requester

class HttpResponseException extends RuntimeException {

    HttpResponseException(String message) {
        super(message)
    }

    HttpResponseException(Throwable cause) {
        super(cause)
    }
}
