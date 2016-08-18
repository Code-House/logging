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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

/**
 * Test of log forwarding (class).
 */
public class ForwardTest extends MockitoTestBase<ForwardLogger> {

    @Test
    public void test_forward_message_to_other_class_category() throws Throwable {
        when(slf4jLogger.isInfoEnabled()).thenReturn(true);

        logger.message();

        verify(slf4jLogger).info("Info message");
    }

}
