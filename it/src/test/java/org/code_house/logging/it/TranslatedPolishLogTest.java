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
import org.junit.runner.RunWith;

import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Basic test of with different resource bundle.
 */
public class TranslatedPolishLogTest extends MockitoTestBase<TranslatedLogger> {

    @Test
    public void test_type_with_polish_i18n() throws Throwable {
        Locale.setDefault(new Locale("pl"));
        when(slf4jLogger.isInfoEnabled()).thenReturn(true);

        logger.defaultMessage();

        verify(slf4jLogger).info("Domyślna wiadomość");
    }

}
