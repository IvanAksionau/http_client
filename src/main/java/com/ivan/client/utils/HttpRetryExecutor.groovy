package com.ivan.client.utils

import com.ivan.client.http.requester.HttpResponse
import groovy.util.logging.Slf4j

import java.time.Duration
import java.time.LocalDateTime

import static java.time.Duration.between

@Slf4j
class HttpRetryExecutor extends RetryExecutor<HttpResponse> {

    Duration poolDuration
    Integer poolIntervalMillis

    HttpRetryExecutor setPool(Duration poolDuration, Integer poolInterval) {
        this.poolDuration = poolDuration
        this.poolIntervalMillis = poolInterval
        this
    }

    @Override
    HttpResponse execute() {
        LocalDateTime start = LocalDateTime.now()
        HttpResponse result = expression.get()
        boolean conditionSucceeded = condition.test(result)

        while (!conditionSucceeded && between(start, LocalDateTime.now()).seconds < poolDuration.seconds) {
            try {
                Thread.sleep(poolIntervalMillis)
            } catch (InterruptedException e) {
                log.error('Some error was thrown during method execution', e)
            }

            result = expression.get()
            log.info('Was made an additional attempt to execute expression:' +
                    " \n ${expression.toString()} ")

            conditionSucceeded = condition.test(result)
        }
        result
    }
}
