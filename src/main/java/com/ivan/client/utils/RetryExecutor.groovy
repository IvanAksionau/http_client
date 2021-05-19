package com.ivan.client.utils

import groovy.util.logging.Slf4j

import java.time.Duration
import java.time.LocalDateTime
import java.util.function.Predicate
import java.util.function.Supplier

import static java.time.Duration.between

@Slf4j
class RetryExecutor<T> {
    private Duration poolDuration
    private Integer poolIntervalMillis

    private Supplier<T> expression = null
    private Predicate<T> condition = null

    RetryExecutor<T> setPool(Duration poolDuration, Integer poolInterval) {
        this.poolDuration = poolDuration
        this.poolIntervalMillis = poolInterval
        this
    }

    RetryExecutor<T> setExpression(Supplier<T> expression) {
        this.expression = expression
        this
    }

    RetryExecutor<T> setCondition(Predicate<T> condition) {
        this.condition = condition
        this
    }

    T execute() {
        LocalDateTime start = LocalDateTime.now()
        T result = expression.get()
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
