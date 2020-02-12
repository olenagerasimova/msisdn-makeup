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
 * Azerbaijan phone numbers format descriptor.
 * @since 1.0
 */
final class AzFormat implements Format {

    @Override
    public @NotNull String prefix() {
        return "994";
    }

    @Override
    public int len() {
        //@checkstyle MagicNumberCheck (1 line)
        return 12;
    }

    @Override
    public boolean suits(final String number) {
        boolean res = false;
        if (number.length() == this.len() && number.startsWith(this.prefix())) {
            res = true;
        } else if (number.length() == this.len() - this.prefix().length()) {
            res = true;
            //@checkstyle MagicNumberCheck (1 line)
        } else if (number.length() == 10 && number.charAt(0) == '0') {
            res = true;
        }
        return res;
    }

    @Override
    public String format(final String number) {
        if (this.suits(number)) {
            final String res;
            if (number.length() == this.len() - this.prefix().length()) {
                res = this.prefix() + number;
            } else if (number.charAt(0) == '0') {
                res = this.prefix() + number.substring(1);
            } else {
                res = number;
            }
            return res;
        }
        throw new IllegalArgumentException(
            String.format(
                "Phone number %s does not suit Azerbaijan format", number
            )
        );
    }
}
