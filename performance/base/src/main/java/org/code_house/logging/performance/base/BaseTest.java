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
package org.code_house.logging.performance.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

/**
 * Base class for performance tests.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public abstract class BaseTest {

    /**
     * Number of iterations during "performance" test.
     */
    public static final int COUNT = 100000;

    /**
     * Perf4j watch.
     */
    private StopWatch watch;

    @Before
    public void startUp() {
        watch = new LoggingStopWatch();
    }

    /**
     * Launch test with perf4j logging.
     */
    @Test
    public final void testPerformance() {
        watch.start();
        int i = 0;
        while (i++ < COUNT) {
            executeLogger(i);
        }
        watch.stop();
    }

    @After
    public void tearDown() {
        watch.stop();
    }

    /**
     * Executes logger with given operation count.
     * 
     * @param iter Iteration count.
     */
    protected abstract void executeLogger(int iter);

}
