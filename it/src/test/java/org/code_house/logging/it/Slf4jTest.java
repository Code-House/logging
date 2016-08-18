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

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

/**
 * Test for argument adoption.
 */
@RunWith(MockitoJUnitRunner.class)
public class Slf4jTest {

    /**
     * An user name.
     */
    private static final String WOMBAT = "Wom#bat";

    /**
     * Mock backend.
     */
    @Mock
    private Logger mock;

    @Test
    public void test_log_adapted_argument() throws Throwable {
        mock.warn("Authorization of user {} failed", WOMBAT, null, WOMBAT);

        verify(mock).warn(eq("Authorization of user {} failed"), Mockito.isA(String.class), Mockito.isNull(), Mockito.isA(String.class));
    }

    @Test
    @Ignore // this test currently fails due to matching problems
    public void test_log() throws Throwable {
        Object[] arguments = new Object[] {WOMBAT, WOMBAT};
        mock.warn("Authorization of user {} failed", arguments);

        ArgumentMatcher<Object[]> argumentMatcher = new ArgumentMatcher<Object[]>() {
            public boolean matches(Object[] argument) {
                return false;
            }
        };
        verify(mock).warn(eq("Authorization of user {} failed"), Mockito.argThat(argumentMatcher));
    }

}
