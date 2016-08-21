/*
 * (C) Copyright 2016 Code-House and others.
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
import org.code_house.logging.api.level.Info;
import org.code_house.logging.api.message.I18n;
import org.code_house.logging.api.message.Message;

/**
 * Basic translated logger.
 *
 * @author Łukasz Dywicki <luke@code-house.org>
 */
@I18n
@Info
public interface TranslatedLogger extends ReplaceableLogger {

    @Message("Default message")
    void defaultMessage();

    @Message("Message with args '{}', '{}'")
    void messageWithArgs(String one, String two);

    @Message("Annotation message")
    void missingTranslatedMessage();

}
