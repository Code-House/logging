/*
 * Copyright (C) 2016 Code-House, Łukasz Dywicki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.code_house.logging.core;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.WeakHashMap;

import org.code_house.logging.core.internal.LoggerInvocationHandler;

/**
 * Wrapper for LoggerFactory which lets to obtain typed logger.
 */
public class LoggerFactory {

    /**
     * Cache for proxy instances. We should have one proxy per logger interface.
     */
    private static Map<Class<?>, Object> loggers = new WeakHashMap<Class<?>, Object>();

    public static <T> T getLogger(Class<T> logger) {
        return proxy(logger);
    }

    public static void removeLogger(Class<?> logger) {
        loggers.remove(logger);
    }

    private static <T> T proxy(Class<T> type) {
        if (!loggers.containsKey(type)) {
            loggers.put(type, Proxy.newProxyInstance(type.getClassLoader(), new Class[] {type}, new LoggerInvocationHandler(type)));
        }
        return type.cast(loggers.get(type));
    }

}
