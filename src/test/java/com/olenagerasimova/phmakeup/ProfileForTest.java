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

import org.cactoos.list.ListOf;
import org.cactoos.list.Mapped;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test for {@link ProfileFor}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public class ProfileForTest {

    @Test
    public void parsesSingleDescriptorCorrectly() {
        MatcherAssert.assertThat(
            new FormatEq(new ProfileFor("13,77").formats().get(0)),
            new IsEqual<>(new FormatFor(13, "77"))
        );
    }

    @Test
    public void parsesShortDescriptorCorrectly() {
        MatcherAssert.assertThat(
            new FormatEq(new ProfileFor("15").formats().get(0)),
            new IsEqual<>(new FormatFor(15, ""))
        );
    }

    @Test
    public void parsesLongDescriptorCorrectly() {
        MatcherAssert.assertThat(
            new Mapped<>(
                FormatEq::new,
                new ProfileFor("13,71;11,7;15,789;12;9,0").formats()
            ),
            new IsIterableContainingInAnyOrder<>(
                new ListOf<Matcher<? super Object>>(
                    new IsEqual<>(new FormatFor(13, "71")),
                    new IsEqual<>(new FormatFor(11, "7")),
                    new IsEqual<>(new FormatFor(15, "789")),
                    new IsEqual<>(new FormatFor(12, "")),
                    new IsEqual<>(new FormatFor(9, "0"))
                )
            )
        );
    }

    @Test
    public void parsesEmptyLine() {
        MatcherAssert.assertThat(
            new Mapped<>(
                FormatEq::new,
                new ProfileFor("").formats()
            ),
            new IsIterableContainingInAnyOrder<>(
                new ListOf<Matcher<? super Object>>(
                    new IsEqual<>(Format.NO_CHECK)
                )
            )
        );
    }
}
