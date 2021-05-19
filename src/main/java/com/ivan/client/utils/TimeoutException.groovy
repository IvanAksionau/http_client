package com.ivan.client.utils

class TimeoutException extends RuntimeException {

    TimeoutException(String message, Throwable cause) {
        super(message, cause)
    }
}
