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
import javax.validation.constraints.NotNull;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.Mapped;

/**
 * Profile for given description. Descriptor format:
 * length,country_code;length,country_code;...
 *
 * Country code can be skipped.
 * For example: 11,7;11;12,380;12,375;12;13
 * @since 1.0
 */
public final class ProfileFor implements Profile {

    /**
     * Profile description.
     */
    private final List<Format> frmts;

    /** Ctor.
     * @param description Profile description
     */
    public ProfileFor(@NotNull final String description) {
        this.frmts = new Mapped<>(
            ProfileFor::parse,
            new IterableOf<>(description.split(";"))
        );
    }

    @Override
    public List<Format> formats() {
        return this.frmts;
    }

    /**
     * Parses string into {@link Format}.
     * @param frm String format representation to parse
     * @return Returns {@link Format} for given string
     * @throws NumberFormatException if string representation is invalid
     */
    private static Format parse(@NotNull final String frm) {
        final Format res;
        final int comma = frm.indexOf(',');
        if (frm.isEmpty()) {
            res = Format.NO_CHECK;
        } else if (comma < 0) {
            res = new FormatFor(Integer.parseInt(frm), "");
        } else if ("11,7".equals(frm)) {
            res = new RuFormat();
        } else if ("12,994".equals(frm)) {
            res = new AzFormat();
        } else {
            res = new FormatFor(
                Integer.parseInt(frm.substring(0, comma)),
                frm.substring(comma + 1)
            );
        }
        return res;
    }
}
