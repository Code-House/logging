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
package org.code_house.logging.api.message;

/**
 * Extension interface which lets adapt given object to another before writing to log.
 * 
 * Usually {@link #adapt(Object)} will return {@link String}, however it's not mandatory.
 * If implementation will return another object logging implementation will use toString call.
 */
public interface TypeAdapter {

    /**
     * Checks if given argument may be adapted to another form.
     * 
     * @param o Object to be adapted. Passed object is never null.
     * @return True if adapter is capable to adapt argument, false instead.
     */
    boolean isSupported(Object o);

    /**
     * Adapts given object before writing to log.
     * 
     * @param o Object to be adapted. Passed object is never null.
     * @return Adapted form of passed object.
     */
    Object adapt(Object o);

}
