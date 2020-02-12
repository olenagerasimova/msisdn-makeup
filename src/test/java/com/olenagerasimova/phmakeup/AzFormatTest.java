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

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for {@link AzFormat}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocVariableCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class AzFormatTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void formatIsCorrect() {
        MatcherAssert.assertThat(
            new FormatEq(new AzFormat()),
            new IsEqual<>(new FormatFor(12, "994"))
        );
    }

    @Test
    public void formatsAlreadyFormattedPhone() {
        final String phone = "994458569658";
        MatcherAssert.assertThat(
            new AzFormat().format(phone),
            new IsEqual<>(phone)
        );
    }

    @Test
    public void formatsPhoneWithMissingPrefix() {
        final String phone = "458569058";
        MatcherAssert.assertThat(
            new AzFormat().format(phone),
            new IsEqual<>(String.join("", "994", phone))
        );
    }

    @Test
    public void formatsPhoneWithZeroPrefix() {
        final String phone = "0854741256";
        MatcherAssert.assertThat(
            new AzFormat().format(phone),
            new IsEqual<>(String.join("", "994", phone.substring(1)))
        );
    }

    @Test
    public void returnsFalseIfLenIsCorrectButPrefixIsWrong() {
        MatcherAssert.assertThat(
            new AzFormat().suits("995588875458"),
            new IsEqual<>(false)
        );
    }

    @Test
    public void returnsFalseIfLenIsTenButStartsNotFromZero() {
        MatcherAssert.assertThat(
            new AzFormat().suits("5856968574"),
            new IsEqual<>(false)
        );
    }

    @Test
    public void throwsExceptionIfPhoneIsNotRussian() {
        final String number = "123";
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage(
            String.format(
                "Phone number %s does not suit Azerbaijan format", number
            )
        );
        new AzFormat().format(number);
    }

}
