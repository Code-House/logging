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
package org.code_house.logging.core.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AnnotatedElement;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.WeakHashMap;

/**
 * An cache bundles.
 *
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class MessageBundles {

    private static final Logger logger = LoggerFactory.getLogger(MessageBundles.class);

    private static final WeakHashMap<AnnotatedElement, ResourceBundle> map = new WeakHashMap<>();

    public static ResourceBundle getBundle(Class<?> type) {
        if (!map.containsKey(type)) {
            ResourceBundle bundle = ResourceBundle.getBundle(type.getName(), Locale.getDefault(), type.getClassLoader());
            try {
                map.put(type, bundle);
            } catch (MissingResourceException e) {
                logger.warn("Could not find resource bundle for type {} and locale {}", type.getName(), Locale.getDefault(), e);
                map.put(type, null);
            }
        }
        return map.get(type);
    }

    public static ResourceBundle getBundle(Package pkg, ClassLoader classLoader) {
        if (!map.containsKey(pkg)) {
            try {
                ResourceBundle bundle = ResourceBundle.getBundle(pkg.getName() + ".Logging", Locale.getDefault(), classLoader);
                map.put(pkg, bundle);
            } catch (MissingResourceException e) {
                logger.warn("Could not find resource bundle for package {} and locale {}", pkg.getName(), Locale.getDefault(), e);
                map.put(pkg, null);
            }
        }
        return map.get(pkg);
    }

}
