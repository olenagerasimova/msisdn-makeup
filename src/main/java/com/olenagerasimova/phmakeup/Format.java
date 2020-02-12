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
 * Phone (msisdn) format: country code and length.
 * @since 1.0
 */
public interface Format {

    /**
     * Format, that checks only maximum length.
     * @checkstyle AnonInnerLengthCheck (30 lines)
     */
    Format NO_CHECK = new Format() {
        @Override
        public @NotNull String prefix() {
            return "";
        }

        @Override
        public int len() {
            //@checkstyle MagicNumberCheck (1 line)
            return 15;
        }

        @Override
        public boolean suits(@NotNull final String number) {
            return number.length() <= this.len();
        }

        @Override
        public @NotNull String format(@NotNull final String number) {
            if (!this.suits(number)) {
                throw new IllegalArgumentException(
                    String.format(
                        "Msisdn %s is longer than allowed (max allowed is %d)",
                        number, this.len()
                    )
                );
            }
            return number;
        }
    };

    /**
     * Format prefix (country code).
     * @return String prefix
     */
    @NotNull String prefix();

    /**
     * Format's length (prefix included).
     * @return Int length
     */
    int len();

    /**
     * Checks whether given phone suits to the format.
     * @param number Clean phone
     * @return True if number suits this format
     */
    boolean suits(@NotNull String number);

    /**
     * Formats given number according to formats prefix and length.
     * @param number Clean phone
     * @return Formatted msisdn
     * @throws IllegalArgumentException If given number cannot be formatted
     */
    @NotNull String format(@NotNull String number);

}
