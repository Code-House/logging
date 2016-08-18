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

import static org.mockito.Mockito.when;

import org.code_house.logging.it.pojo.User;
import org.junit.Test;

/**
 * Test for argument adoption.
 */
public class AdapterTest extends MockitoTestBase<AdapterLogger> {

    /**
     * An user name.
     */
    private static final String WOMBAT = "Wom#bat";

    @Test
    public void test_log_adapted_argument() throws Throwable {
        final User user = new User(WOMBAT);

        when(slf4jLogger.isWarnEnabled()).thenReturn(true);

        logger.authorizationFailed(user);

        //verify(slf4jLogger).warn(eq("Authorization of user {} failed"), eq(WOMBAT));
        //verify(mock).warn(eq("Authorization of user {} failed"), (String) anyVararg());
        //verify(mock).warn(eq("Authorization of user {} failed"), any());
        //verify(mock).warn(eq("Authorization of user {} failed"), AdditionalMatchers.aryEq(new Object[]{WOMBAT}));
        //verify(mock).warn(eq("Authorization of user {} failed"), eq(WOMBAT));
    }

}
