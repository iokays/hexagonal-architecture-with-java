package com.iokays.common.distributed.lock;

import java.lang.reflect.Method;

/**
 * Distributed Lock Expression Root Object
 *
 * @param args
 */
record DistributedLockExpressionRootObject(Method method, Object[] args) {
}
