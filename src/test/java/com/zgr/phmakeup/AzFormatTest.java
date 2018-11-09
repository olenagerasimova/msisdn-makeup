/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

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
