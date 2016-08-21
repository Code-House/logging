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
package org.code_house.logging.api.message;

import java.lang.annotation.*;

/**
 * This annotation is used to mark that messages should be looked up in Resource Bundle.
 *
 * Default message is always read from Message annotation, in case if there is another variant available for other locale
 * it will place in log file it. If annotation is specified at package level then Message Bundle called "Logging" is used,
 * otherwise simple class name is used.
 * For package resource bundles keys are created from simple class name and method. For type level translations only method
 * name is being used as message key.
 * Formatting of message arguments such {0,time}, {1,date} in not available since all arguments are passed to slf4j, thus
 * the same order of elements is forced and usage of {} for arguments instead of indexed variant.
 *
 *
 * @author Łukasz Dywicki <luke@code-house.org>
 */
@Documented
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface I18n {

    /**
     * Alternative name for resource bundle.
     */
    String value() default "";

}
