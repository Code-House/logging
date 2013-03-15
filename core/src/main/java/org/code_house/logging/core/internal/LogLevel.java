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
        public void log(Logger logger, String message, Object ... arguments) {
            logger.trace(message, arguments);
        }
    },
    DEBUG(Debug.class) {
        @Override
        public void log(Logger logger, String message, Object ... arguments) {
            logger.debug(message, arguments);
        }
    },
    INFO(Info.class) {
        @Override
        public void log(Logger logger, String message, Object ... arguments) {
            logger.info(message, arguments);
        }
    },
    WARNING(Warning.class) {
        @Override
        public void log(Logger logger, String message, Object ... arguments) {
            logger.warn(message, arguments);
        }
    },
    ERROR(Error.class) {
        @Override
        public void log(Logger logger, String message, Object ... arguments) {
            logger.error(message, arguments);
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
    public abstract void log(Logger logger, String message, Object ... arguments);

}