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
 * Test for {@link PhoneFor}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle JavadocVariableCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class PhoneForTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void makeupsPhone() {
        MatcherAssert.assertThat(
            new PhoneFor("8(909) 695-952-12", new ProfileFor("11,7;13;12,8"))
                .makeup(),
            new IsEqual<>("890969595212")
        );
    }

    @Test
    public void makeupsPhoneWithoutPrefix() {
        MatcherAssert.assertThat(
            new PhoneFor("(909) 695-952-12", new ProfileFor("13,71;13;10,8"))
                .makeup(),
            new IsEqual<>("7190969595212")
        );
    }

    @Test
    public void makeupsRussianPhoneWithEightPrefix() {
        MatcherAssert.assertThat(
            new PhoneFor("8(909) 691-95-12", new ProfileFor("11,7;10;14,84"))
                .makeup(),
            new IsEqual<>("79096919512")
        );
    }

    @Test
    public void makeupsRussianPhone() {
        MatcherAssert.assertThat(
            new PhoneFor("7(909) 695-92-12", Profile.RU).makeup(),
            new IsEqual<>("79096959212")
        );
    }

    @Test
    public void doesNothingIfPhoneAlreadyOk() {
        final String phone = "4595874569654";
        MatcherAssert.assertThat(
            new PhoneFor(phone, Profile.UNLIMITED).makeup(),
            new IsEqual<>(phone)
        );
    }

    @Test
    public void failsWhenFormatNotFound() {
        final String phone = "32 458 452 45 46";
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage(
            String.format(
                "Number %s does not correspond to given profile", phone
            )
        );
        new PhoneFor(phone, new ProfileFor("10,5;12,33")).makeup();
    }

    @Test
    public void failsIfPhoneIsTooLong() {
        final String phone = "456 (458) 458-547-898-99";
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage(
            String.format(
                "Number %s does not correspond to given profile", phone
            )
        );
        new PhoneFor(phone, Profile.UNLIMITED).makeup();
    }
}
