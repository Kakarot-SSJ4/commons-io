/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.io.input;

import java.io.InputStream;

/**
 *
 * An {@link InputStream} that infinitely repeats provided bytes.
 * <p>
 * Closing a <code>InfiniteCircularInputStream</code> has no effect. The methods in
 * this class can be called after the stream has been closed without generating
 * an <code>IOException</code>.
 * </p>
 */
public class InfiniteCircularInputStream extends InputStream {

    final private byte[] repeatedContent;
    private int position = -1;

    /**
     * Creates a InfiniteCircularStream from the specified array of chars.
     *
     * @param repeatedContent
     *            Input buffer to be repeated (not copied)
     */
    public InfiniteCircularInputStream(final byte[] repeatedContent) {
        this.repeatedContent = repeatedContent;
    }

    @Override
    @SuppressWarnings("index") /* in repeatedContent[position], position will never be negative as it is initialized to -1, but (position + 1)
     % repeatedContent.length ensures it to be non negative  
     */
    public int read() {
        position = (position + 1) % repeatedContent.length;
        return repeatedContent[position] & 0xff;
    }

}
