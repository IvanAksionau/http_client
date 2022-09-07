package com.ivan.client.utils

import java.util.function.Predicate
import java.util.function.Supplier

abstract class RetryExecutor<T> {

    protected Supplier<T> expression = null
    protected Predicate<T> condition = null

    RetryExecutor<T> setExpression(Supplier<T> expression) {
        this.expression = expression
        this
    }

    RetryExecutor<T> setCondition(Predicate<T> condition) {
        this.condition = condition
        this
    }

    abstract T execute()
}
