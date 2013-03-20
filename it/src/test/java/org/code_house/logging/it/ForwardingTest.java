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

import static org.code_house.logging.mock.EasyMockLoggerFactory.expectLogger;
import static org.code_house.logging.mock.EasyMockLoggerFactory.removeLogger;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.code_house.logging.core.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;

/**
 * Basic test of logging utility.
 */
@Ignore
@RunWith(BlockJUnit4ClassRunner.class)
public class ForwardingTest {

    /**
     * Example logger.
     */
    private ExampleLogger logger;

    /**
     * Mock backend.
     */
    private Logger mock;

    @Before
    public void startUp() {
        mock = expectLogger(ExampleLogger.class);
        logger = LoggerFactory.getLogger(ExampleLogger.class);
    }

    @Test
    public void testMethodWithMessageAnnotationAndNoLevelSpecified() throws Throwable {
        mock.info("Info message");
        expectLastCall();
        replay(mock);

        logger.message();

        verify(mock);
    }

    @Test
    public void testMethodWithMessageAndDebugAnnotation() throws Throwable {
        mock.debug("Debug message");
        expectLastCall();
        replay(mock);

        logger.debugMessage();

        verify(mock);
    }

    @After
    public void tearDown() {
        removeLogger(mock.getName());
    }

}
