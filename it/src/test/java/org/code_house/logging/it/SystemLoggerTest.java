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

import org.junit.Test;
import org.slf4j.impl.StaticMDCBinder;
import org.slf4j.spi.MDCAdapter;

import static org.code_house.logging.core.internal.LoggerInvocationHandler.LOGGING_CODE_MDC;
import static org.mockito.Mockito.*;

/**
 * Basic test of of @Code annotations.
 */
public class SystemLoggerTest extends MockitoTestBase<SystemLogger> {

    @Test
    public void test_method_with_code() throws Throwable {
        when(slf4jLogger.isInfoEnabled()).thenReturn(true);
        MDCAdapter mdcAdapter = StaticMDCBinder.getSingleton().getMDCA();
        // there might be other calls to MDC, so we need to cleanup mock state
        reset(mdcAdapter);

        logger.noMethodCode("asdf");

        verify(mdcAdapter).put(eq(LOGGING_CODE_MDC), eq("PKG-SYS-03"));
        verify(mdcAdapter).remove(LOGGING_CODE_MDC);

        verify(slf4jLogger).info("Method {} do not ship @Code", new Object[] {"asdf"});
    }

}
