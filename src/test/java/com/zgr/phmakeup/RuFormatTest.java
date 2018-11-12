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
 * Test for {@link RuFormat}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocVariableCheck (500 lines)
 */
public class RuFormatTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void formatIsCorrect() {
        MatcherAssert.assertThat(
            new FormatEq(new RuFormat()),
            new IsEqual<>(new FormatFor(11, "7"))
        );
    }

    @Test
    public void formatsAlreadyFormattedPhone() {
        final String phone = "79058545856";
        MatcherAssert.assertThat(
            new RuFormat().format(phone),
            new IsEqual<>(phone)
        );
    }

    @Test
    public void formatsPhoneWithMissingPrefix() {
        final String phone = "9058545858";
        MatcherAssert.assertThat(
            new RuFormat().format(phone),
            new IsEqual<>(String.join("", "7", phone))
        );
    }

    @Test
    public void formatsPhoneWithEightPrefix() {
        final String phone = "89051545858";
        MatcherAssert.assertThat(
            new RuFormat().format(phone),
            new IsEqual<>(String.join("", "7", phone.substring(1)))
        );
    }

    @Test
    public void throwsExceptionIfPhoneIsNotRussian() {
        final String number = "123";
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage(
            String.format(
                "Phone number %s does not suit Russian format", number
            )
        );
        new RuFormat().format(number);
    }

    @Test
    public void returnsFalseIfPrefixIsCorrectButLenIsWrong() {
        MatcherAssert.assertThat(
            new RuFormat().suits("95851254585"),
            new IsEqual<>(false)
        );
    }
}
