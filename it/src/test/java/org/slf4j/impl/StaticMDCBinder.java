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
package org.slf4j.impl;

import org.mockito.Mockito;
import org.slf4j.spi.MDCAdapter;

/**
 * Implementation of slf4j mdc binding which just returns mockito mock.
 *
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class StaticMDCBinder {

    public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

    private final MDCAdapter adapter = Mockito.mock(MDCAdapter.class);

    public static final StaticMDCBinder getSingleton() {
        return SINGLETON;
    }

    public MDCAdapter getMDCA() {
        return adapter;
    }

    public String getMDCAdapterClassStr() {
        return "<mock>";
    }

}
