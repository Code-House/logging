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

import java.util.Locale;

import static org.mockito.Mockito.*;

/**
 * Basic test of translation.
 */
public class TranslatedLogTest extends MockitoTestBase<TranslatedLogger> {

    static {
        Locale.setDefault(Locale.US);
    }

    @Test
    public void test_type_with_i18n() throws Throwable {
        Locale.setDefault(Locale.US);
        when(slf4jLogger.isInfoEnabled()).thenReturn(true);

        logger.defaultMessage();

        verify(slf4jLogger).info("Default message");
    }

    @Test
    public void test_type_with_i18n_and_args() throws Throwable {
        when(slf4jLogger.isInfoEnabled()).thenReturn(true);

        logger.messageWithArgs("one", "two");

        verify(slf4jLogger).info("Message from file with args '{}', '{}'", new Object[] {"one", "two"});
    }


    @Test
    public void test_type_with_i18n_and_missing_translation() throws Throwable {
        when(slf4jLogger.isInfoEnabled()).thenReturn(true);

        logger.missingTranslatedMessage();

        verify(slf4jLogger).info("Annotation message");
    }

}
