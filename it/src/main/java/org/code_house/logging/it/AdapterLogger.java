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

import org.code_house.logging.api.ReplaceableLogger;
import org.code_house.logging.api.level.Warning;
import org.code_house.logging.api.message.Adapter;
import org.code_house.logging.api.message.Message;
import org.code_house.logging.it.pojo.AdaptUser;
import org.code_house.logging.it.pojo.AdaptableUser;
import org.code_house.logging.it.pojo.User;
import org.code_house.logging.it.pojo.UserPojoAdapter;

/**
 * Logger which uses @Adapter annotations.
 */
public interface AdapterLogger extends ReplaceableLogger {

    /**
     * Dummy log statement.
     * 
     * @param user Dummy parameter.
     */
    @Warning
    @Message("Authorization of user {} failed")
    void authorizationFailed(@Adapter(UserPojoAdapter.class) User user);

    /**
     * Dummy log statement.
     * 
     * @param user Dummy parameter.
     */
    @Warning
    @Message("Authorization of user {} succeed")
    void authorizationSuccessful(AdaptableUser user);

    /**
     * Dummy log statement.
     * 
     * @param user Dummy parameter.
     */
    @Warning
    @Message("User {} logged out")
    void logout(AdaptUser user);

}
