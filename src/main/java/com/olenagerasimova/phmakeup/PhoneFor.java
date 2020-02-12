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

/**
 * Phone for number and profile.
 * @since 1.0
 */
public final class PhoneFor implements Phone {
    /**
     * Phone number.
     */
    private final String number;

    /**
     * Profile.
     */
    private final Profile profile;

    /**
     * Ctor.
     * @param number Phone number
     * @param profile Phone profile
     */
    public PhoneFor(final String number, final Profile profile) {
        this.number = number;
        this.profile = profile;
    }

    @Override
    public String makeup() {
        final String clean = this.number.replaceAll("[^\\d]", "");
        for (final Format format : this.profile.formats()) {
            if (format.suits(clean)) {
                return format.format(clean);
            }
        }
        throw new IllegalArgumentException(
            String.format(
                "Number %s does not correspond to given profile",
                this.number
            )
        );
    }
}
