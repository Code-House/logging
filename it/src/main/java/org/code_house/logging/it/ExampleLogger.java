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

import org.code_house.logging.api.Ignore;
import org.code_house.logging.api.level.Debug;
import org.code_house.logging.api.message.Message;

/**
 * Example logger.
 */
public interface ExampleLogger {

    /**
     * Log example message.
     */
    @Message("Info message")
    void message();

    /**
     * Log example message at DEBUG level.
     */
    @Debug
    @Message("Debug message")
    void debugMessage();

    /**
     * An anonymous method.
     * 
     * Call of this method will cause runtime exception since it does not specify message for log.
     */
    void anonymous();

    /**
     * An ignored method.
     */
    @Ignore
    void ignore();

}
