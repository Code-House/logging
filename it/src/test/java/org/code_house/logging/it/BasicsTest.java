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
package org.code_house.logging.it;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.code_house.logging.api.NoMessageSpecified;
import org.junit.Test;

/**
 * Basic test of logging utility.
 */
public class BasicsTest extends MockitoTestBase<ExampleLogger> {

    @Test
    public void test_method_with_message_annotation_and_debug_level_specified() throws Throwable {
        when(slf4jLogger.isDebugEnabled()).thenReturn(true);

        logger.debugMessage();

        verify(slf4jLogger).debug("Debug message");
    }

    @Test(expected = NoMessageSpecified.class)
    public void test_method_with_no_message_annotation() throws Throwable {
        when(slf4jLogger.isInfoEnabled()).thenReturn(true);

        logger.anonymous();
    }

    @Test
    public void test_method_with_no_message_and_ignore_annotation() throws Throwable {
        logger.ignore();

        verify(slf4jLogger, never()).isInfoEnabled();
    }

    @Test
    public void test_method_with_message_debug_and_ignore_annotation() throws Throwable {
        logger.debugIgnore();

        verify(slf4jLogger, never()).isDebugEnabled();
    }

    @Test
    public void test_method_with_message_annotation_and_no_level_specified() throws Throwable {
        when(slf4jLogger.isInfoEnabled()).thenReturn(true);

        logger.message();

        verify(slf4jLogger).info("Info message");
    }

}
