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

import javax.validation.constraints.NotNull;

/**
 * Phone number format: required length and prefix. Prefix is actually
 * country code.
 * @since 1.0
 */
final class FormatFor implements Format {

    /**
     * Phone length.
     */
    private final int lngth;

    /**
     * Phone prefix, can be an empty string.
     */
    private final String prfx;

    /**
     * Ctor.
     * @param len FormatFor length
     * @param prefix FormatFor prefix
     */
    FormatFor(final int len, @NotNull final String prefix) {
        this.lngth = len;
        this.prfx = prefix;
    }

    @Override
    public String prefix() {
        return this.prfx;
    }

    @Override
    public int len() {
        return this.lngth;
    }

    @Override
    public boolean suits(final String number) {
        return number.length() == this.lngth
            && number.startsWith(this.prfx)
            || number.length() == this.lngth - this.prfx.length();
    }

    @Override
    public String format(final String number) {
        if (this.suits(number)) {
            final String res;
            if (number.length() == this.lngth - this.prfx.length()) {
                res = this.prfx + number;
            } else {
                res = number;
            }
            return res;
        }
        throw new IllegalArgumentException(
            String.format(
                "Phone number %s does not suit format len = %d, prefix = %s",
                number, this.lngth, this.prfx
            )
        );
    }
}
