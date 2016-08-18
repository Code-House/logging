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
package org.code_house.logging.it;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.code_house.logging.api.ReplaceableLogger;
import org.code_house.logging.core.LoggerFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

/**
 * Base class for unit tests using mockito for mocking/stubbing.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class MockitoTestBase<T extends ReplaceableLogger> {

    /**
     * Mock backend.
     */
    @Mock
    protected Logger slf4jLogger;

    protected T logger;

    protected MockitoTestBase() {
        logger = LoggerFactory.getLogger(MockitoTestBase.<T>getGenericTypeAt(getClass(), 0));
    }

    @Before
    public void setUp() {
        logger.setLogger(slf4jLogger);
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getGenericTypeAt(Class<?> type, int position) {
        Type superclass = type.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType genericTypeClass = (ParameterizedType) superclass;
            Type argument = genericTypeClass.getActualTypeArguments()[position];
            if (argument instanceof Class) {
                return (Class<T>) argument;
            }
        }
        return null;
    }

}
