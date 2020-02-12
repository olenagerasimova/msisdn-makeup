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
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Test;

/**
 * Test for {@link FormatEq}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public class FormatEqTest {

    @Test
    public void returnsCorrectPrefix() {
        final FormatFor frmt = new FormatFor(12, "55");
        MatcherAssert.assertThat(
            new FormatEq(frmt).prefix(),
            new IsEqual<>(frmt.prefix())
        );
    }

    @Test
    public void returnsCorrectLen() {
        final FormatFor frmt = new FormatFor(11, "41");
        MatcherAssert.assertThat(
            new FormatEq(frmt).len(),
            new IsEqual<>(frmt.len())
        );
    }

    @Test
    public void equalsItself() {
        final FormatFor frmt = new FormatFor(11, "7");
        MatcherAssert.assertThat(
            new FormatEq(frmt),
            new IsEqual<>(frmt)
        );
    }

    @Test
    public void sameObjectsAreEqual() {
        MatcherAssert.assertThat(
            new FormatEq(new FormatFor(12, "8")),
            new IsEqual<>(new FormatFor(12, "8"))
        );
    }

    @Test
    public void notEqualIfLenDiffers() {
        MatcherAssert.assertThat(
            new FormatEq(new FormatFor(11, "8")),
            new IsNot<>(new IsEqual<>(new FormatFor(12, "8")))
        );
    }

    @Test
    public void notEqualsIfFormatDiffers() {
        MatcherAssert.assertThat(
            new FormatEq(new FormatFor(12, "9")),
            new IsNot<>(new IsEqual<>(new FormatFor(12, "8")))
        );
    }

    @Test
    public void differentFormatsAreNotEqual() {
        MatcherAssert.assertThat(
            new FormatEq(new FormatFor(10, "9")),
            new IsNot<>(new IsEqual<>(new FormatFor(12, "8")))
        );
    }

    @Test
    public void notEqualsString() {
        MatcherAssert.assertThat(
            new FormatEq(new FormatFor(5, "45")),
            new IsNot<>(new IsEqual<>("hi"))
        );
    }

    @Test
    public void notEqualsNull() {
        MatcherAssert.assertThat(
            new FormatEq(new FormatFor(8, "41")),
            new IsNot<>(new IsEqual<>(null))
        );
    }

    @Test
    public void calcHashCorrectly() {
        MatcherAssert.assertThat(
            new FormatEq(new FormatFor(11, "47")).hashCode(),
            new IsEqual<>(Objects.hash(11, "47"))
        );
    }
}
