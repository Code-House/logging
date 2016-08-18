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
package org.code_house.logging.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Pattern for codes used with logging.
 *
 * Be aware that there might be multiple codes specified at different levels - method and type. Pattern is looked
 * up in same order, first on method, then on type and at the end on package level.
 */
@Documented
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pattern {

    /**
     * Return pattern for error code.
     * 
     * Pattern must contain {} signs to let place a code value from method. Optionally second placeholder is supported
     * for code specified at type level. Pattern at package level can contain a logical code for package.
     */
    String value();

}
