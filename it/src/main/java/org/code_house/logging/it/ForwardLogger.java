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

import org.code_house.logging.api.Category;
import org.code_house.logging.api.ReplaceableLogger;
import org.code_house.logging.api.message.Message;
import org.code_house.logging.core.format.ClassAdapter;

/**
 * Logger which forwards messages to other category.
 */
@Category(ClassAdapter.class)
public interface ForwardLogger extends ReplaceableLogger {

    /**
     * Log example message.
     */
    @Message("Info message")
    void message();

}
