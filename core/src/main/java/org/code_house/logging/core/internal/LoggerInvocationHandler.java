/*
 * Copyright (C) 2013 Code-House, Lukasz Dywicki.
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
package org.code_house.logging.core.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

import org.code_house.logging.api.Category;
import org.code_house.logging.api.Code;
import org.code_house.logging.api.Ignore;
import org.code_house.logging.api.NoMessageSpecified;
import org.code_house.logging.api.Pattern;
import org.code_house.logging.api.level.Debug;
import org.code_house.logging.api.level.Error;
import org.code_house.logging.api.level.Info;
import org.code_house.logging.api.level.Trace;
import org.code_house.logging.api.level.Warning;
import org.code_house.logging.api.message.Adaptable;
import org.code_house.logging.api.message.Adapter;
import org.code_house.logging.api.message.Message;
import org.code_house.logging.api.message.TypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.helpers.MessageFormatter;

/**
 * Invocation handler to do magic - create log entries from annotations.
 */
public class LoggerInvocationHandler implements InvocationHandler {

    /**
     * Method log level cache.
     */
    private Map<Method, LogLevel> levels = new WeakHashMap<Method, LogLevel>();

    /**
     * Method messages cache.
     */
    private Map<Method, String> messages = new WeakHashMap<Method, String>();

    /**
     * Logger category.
     */
    private final Class<?> type;

    private Logger logger;

    public LoggerInvocationHandler(Class<?> type) {
        this(type, getLogger(type));
    }

    public LoggerInvocationHandler(Class<?> type, Logger logger) {
        this.type = type;
        this.logger = logger;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Ignore.class)) {
            return null;
        }

        LogLevel level = getLogLevel(method);
        if (level.disabled(logger)) {
            return null;
        }
        String message = getLogMessage(method);

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Object[] formatted;
        if (parameterAnnotations.length == 0) {
            formatted = args;
        } else {
            formatted = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null && parameterAnnotations[i].length > 0) {
                    TypeAdapter formatter = getFormatter(parameterAnnotations[i]);
                    if (formatter != null && formatter.isSupported(args[i])) {
                        formatted[i] = formatter.adapt(args[i]);
                    } else {
                        formatted[i] = args[i];
                    }
                } else if (args[i] != null && args[i] instanceof Adaptable) {
                    args[i] = ((Adaptable) args[i]).format();
                } else {
                    formatted[i] = args[i];
                }
            }
        }

        String code = getCode(method);
        if (code != null) {
            MDC.put("logging.code", code);
        }

        level.log(logger, message, formatted);

        if (code != null) {
            MDC.remove("logging.code");
        }

        return null;
    }

    private String getCode(Method method) {
        Code code = getCode0(method);
        if (code == null) {
            code = getCode0(type);
        }

        if (code == null) {
            return null;
        }

        String pattern = null;
        if (type.isAnnotationPresent(Pattern.class)) {
            pattern = type.getAnnotation(Pattern.class).value();
        }

        return pattern == null ? code.value() : MessageFormatter.format(pattern, code.value()).getMessage();
    }

    private Code getCode0(AnnotatedElement element) {
        return element.isAnnotationPresent(Code.class) ? element.getAnnotation(Code.class) : null;
    }

    private TypeAdapter getFormatter(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Adapter) {
                try {
                    return ((Adapter) annotation).value().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getLogMessage(Method method) {
        if (!messages.containsKey(method)) {
            if (!method.isAnnotationPresent(Message.class)) {
                throw new NoMessageSpecified(method);
            }
            messages.put(method, method.getAnnotation(Message.class).value());
        }
        return messages.get(method);
    }

    private LogLevel getLogLevel(Method method) {
        if (!levels.containsKey(method)) {
            LogLevel level = getLogLevel0(method);
            if (level == null) {
                level = getLogLevel0(type);
            }
            levels.put(method, level == null ? LogLevel.INFO : level);
        }
        return levels.get(method);
    }

    private LogLevel getLogLevel0(AnnotatedElement object) {
        LogLevel level = null;
        if (object.isAnnotationPresent(Trace.class)) {
            level = LogLevel.TRACE;
        } else if (object.isAnnotationPresent(Debug.class)) {
            level = LogLevel.DEBUG;
        } else if (object.isAnnotationPresent(Info.class)) {
            level = LogLevel.INFO;
        } else if (object.isAnnotationPresent(Warning.class)) {
            level = LogLevel.WARNING;
        } else if (object.isAnnotationPresent(Error.class)) {
            level = LogLevel.ERROR;
        }
        return level;
    }

    private static Logger getLogger(Class<?> type) {
        Object category = getCategory(type);
        return category instanceof Class<?> ? LoggerFactory.getLogger((Class<?>) category) : LoggerFactory.getLogger(category.toString());
    }

    private static Object getCategory(Class<?> type) {
        if (type.isAnnotationPresent(Category.class)) {
            Category category = type.getAnnotation(Category.class);
            return category.usePackage() ? category.value().getPackage().getName() : category.value();
        }
        return type;
    }

}
