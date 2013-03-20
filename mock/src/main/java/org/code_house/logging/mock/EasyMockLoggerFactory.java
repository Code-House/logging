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
package org.code_house.logging.mock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * Logger factory used to replace logging backend with mock logger.
 */
public class EasyMockLoggerFactory implements ILoggerFactory {

    private static Map<String, Logger> loggerCache = new HashMap<String, Logger>();

    public static Logger expectLogger(String name) {
        if (loggerCache.containsKey(name)) {
            throw new AssertionError("Mock for logger " + name + " already exists");
        }
        Logger mock = createMock(Logger.class);
        expect(mock.getName()).andReturn(name).anyTimes();
        loggerCache.put(name, mock);
        return mock;
    }

    public static Logger expectLogger(Class<?> name) {
        return expectLogger(name.getName());
    }

    /**
     * {@inheritDoc}
     */
    public Logger getLogger(String name) {
        if (!loggerCache.containsKey(name)) {
            throw new AssertionError("Unexpected logger lookup " + name);
        }
        return loggerCache.get(name);
    }

    public static void removeLogger(String name) {
        loggerCache.remove(name);
    }

}
