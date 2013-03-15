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
package org.code_house.logging.core.internal;

import java.lang.reflect.Method;

import org.code_house.logging.api.Category;
import org.code_house.logging.api.Code;
import org.code_house.logging.api.Pattern;
import org.code_house.logging.api.level.Trace;
import org.code_house.logging.api.message.Adapter;
import org.code_house.logging.api.message.Message;
import org.code_house.logging.core.LoggerFactory;
import org.code_house.logging.core.format.MethodAdapter;

/**
 * Logger used by logging library.
 */
@Trace
@Pattern("LC{}")
@Category(value = LoggerFactory.class, usePackage = true)
public interface SystemLogger {

    /**
     * Stores information about ignored call.
     * 
     * @param method Method to ignore.
     */
    @Message("Ignoring call {}, method has @Ignore annotation")
    @Code("01") void ignoring(@Adapter(MethodAdapter.class) Method method);

    /**
     * Information about lack of parameter annotations.
     * 
     * @param method Method to ignore.
     */
    @Message("Call on {} did not discover custom @Adapter annotations")
    @Code("02") void noParameterAnnotations(@Adapter(MethodAdapter.class) Method method);

    @Message("Method {} do not ship @Code")
    @Code("03") void noMethodCode(@Adapter(MethodAdapter.class) Method method);

    @Message("Both type and method {} don't have @Code")
    @Code("04") void noCode(@Adapter(MethodAdapter.class) Method method);

    @Message("Method {} have code {}")
    @Code("05") void code(@Adapter(MethodAdapter.class) Method method, String code);

}
