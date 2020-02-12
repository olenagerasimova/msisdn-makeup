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

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Implementation of {@link Format} with equals. Useful in tests.
 * @since 1.0
 */
final class FormatEq implements Format {

    /**
     * Origin format.
     */
    private final Format origin;

    /**
     * Ctor.
     * @param origin Format
     */
    FormatEq(final Format origin) {
        this.origin = origin;
    }

    @Override
    public @NotNull String prefix() {
        return this.origin.prefix();
    }

    @Override
    public int len() {
        return this.origin.len();
    }

    @Override
    public boolean suits(final String number) {
        return this.origin.suits(number);
    }

    @Override
    public String format(final String number) {
        return this.origin.format(number);
    }

    @Override
    public boolean equals(final Object that) {
        return that instanceof Format
            && (this == that || this.len() == ((Format) that).len()
            && Objects.equals(this.prefix(), ((Format) that).prefix()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.len(), this.prefix());
    }
}
