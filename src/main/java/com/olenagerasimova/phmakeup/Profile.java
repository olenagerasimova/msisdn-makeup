/*
 * MIT License
 *
 * Copyright (c) 2020 olenagerasimova
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.olenagerasimova.phmakeup;

import java.util.List;
import org.cactoos.list.ListOf;

/**
 * Msisdn profile.
 * @since 1.0
 */
public interface Profile {

    /**
     * Russian msisdn profile.
     * @since 1.0
     */
    Profile RU = () -> new ListOf<>(new RuFormat());

    /**
     * Profile, limited only by maximum msisdn length. Corresponds to mobicont
     * ValidMSISDNFormat.NO_CHECK_FORMAT_ID.
     * @since 1.0
     * @checkstyle MagicNumberCheck (5 lines)
     */
    Profile UNLIMITED = () -> new ListOf<>(Format.NO_CHECK);

    /**
     * Returns allowed formats for profile.
     * @return Iterable of {@link FormatFor}
     */
    List<Format> formats();
}
