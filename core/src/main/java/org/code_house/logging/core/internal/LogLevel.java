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
package org.code_house.logging.core.internal;

import java.lang.annotation.Annotation;

import org.code_house.logging.api.level.Debug;
import org.code_house.logging.api.level.Error;
import org.code_house.logging.api.level.Info;
import org.code_house.logging.api.level.Trace;
import org.code_house.logging.api.level.Warning;
import org.slf4j.Logger;

/**
 * Enumeration used internally to map annotations to log levels.
 */
public enum LogLevel {

    /**
     * Trace level.
     */
    TRACE(Trace.class) {
        @Override
        public void log(Logger logger, String message, Object[] arguments) {
            if (arguments == null) {
                logger.trace(message);
            } else {
                logger.trace(message, arguments);
            }
        }

        @Override
        public boolean disabled(Logger logger) {
            return !logger.isTraceEnabled();
        }
    },

    /**
     * Debug level.
     */
    DEBUG(Debug.class) {
        @Override
        public void log(Logger logger, String message, Object[] arguments) {
            if (arguments == null) {
                logger.debug(message);
            } else {
                logger.debug(message, arguments);
            }
        }

        @Override
        public boolean disabled(Logger logger) {
            return !logger.isDebugEnabled();
        }
    },

    /**
     * Info level.
     */
    INFO(Info.class) {
        @Override
        public void log(Logger logger, String message, Object[] arguments) {
            if (arguments == null) {
                logger.info(message);
            } else {
                logger.info(message, arguments);
            }
        }

        @Override
        public boolean disabled(Logger logger) {
            return !logger.isInfoEnabled();
        }
    },

    /**
     * Warning level.
     */
    WARNING(Warning.class) {
        @Override
        public void log(Logger logger, String message, Object[] arguments) {
            if (arguments == null) {
                logger.warn(message);
            } else {
                logger.warn(message, arguments);
            }
        }

        @Override
        public boolean disabled(Logger logger) {
            return !logger.isWarnEnabled();
        }
    },

    /**
     * Error level.
     */
    ERROR(Error.class) {
        @Override
        public void log(Logger logger, String message, Object[] arguments) {
            if (arguments == null) {
                logger.error(message);
            } else {
                logger.error(message, arguments);
            }
        }

        @Override
        public boolean disabled(Logger logger) {
            return !logger.isErrorEnabled();
        }
    };

    /**
     * Associated annotation.
     */
    private final Class<? extends Annotation> annotation;

    /**
     * Map enumeration to annotation.
     * 
     * @param annotation Annotation type.
     */
    private LogLevel(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
        
    }

    /**
     * Abstract method to be overridden in enumerations designed to control logger call.
     * 
     * @param logger Logger instance.
     * @param message Log entry message.
     * @param arguments Arguments (after adapting).
     */
    public abstract void log(Logger logger, String message, Object[] arguments);

    /**
     * Abstract method to be overridden in enumerations designed to verify if logger is interested
     * in given call.
     * 
     * @param logger Logger instance.
     * @return True if logger supports given log level.
     */
    public abstract boolean disabled(Logger logger);

}