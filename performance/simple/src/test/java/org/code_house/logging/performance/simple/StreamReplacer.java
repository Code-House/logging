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
package org.code_house.logging.performance.simple;

import java.io.PrintStream;

/**
 * Utility class to replace System.out and System.err.
 */
public class StreamReplacer {

    /**
     * System out.
     */
    private PrintStream outputStream;

    /**
     * System err.
     */
    private PrintStream errorStream;

    /**
     * Take system out/err and put mock stream.
     */
    public final void take() {
        outputStream = System.out;
        errorStream = System.err;
        PrintStream mock = new PrintStream(new NoopOutputStream(), true);
        System.setOut(mock);
        System.setErr(mock);
    }

    /**
     * Restore out/err.
     */
    public final void giveBack() {
        System.setErr(errorStream);
        System.setOut(outputStream);
    }

}
