/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import java.util.Objects;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Test;

/**
 * Test for {@link Format.FormatEq}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public class FormatEqTest {

    @Test
    public void returnsCorrectPrefix() {
        final FormatFor frmt = new FormatFor(12, "55");
        MatcherAssert.assertThat(
            new Format.FormatEq(frmt).prefix(),
            new IsEqual<>(frmt.prefix())
        );
    }

    @Test
    public void returnsCorrectLen() {
        final FormatFor frmt = new FormatFor(11, "41");
        MatcherAssert.assertThat(
            new Format.FormatEq(frmt).len(),
            new IsEqual<>(frmt.len())
        );
    }

    @Test
    public void equalsItself() {
        final FormatFor frmt = new FormatFor(11, "7");
        MatcherAssert.assertThat(
            new Format.FormatEq(frmt),
            new IsEqual<>(frmt)
        );
    }

    @Test
    public void sameObjectsAreEqual() {
        MatcherAssert.assertThat(
            new Format.FormatEq(new FormatFor(12, "8")),
            new IsEqual<>(new FormatFor(12, "8"))
        );
    }

    @Test
    public void notEqualIfLenDiffers() {
        MatcherAssert.assertThat(
            new Format.FormatEq(new FormatFor(11, "8")),
            new IsNot<>(new IsEqual<>(new FormatFor(12, "8")))
        );
    }

    @Test
    public void notEqualsIfFormatDiffers() {
        MatcherAssert.assertThat(
            new Format.FormatEq(new FormatFor(12, "9")),
            new IsNot<>(new IsEqual<>(new FormatFor(12, "8")))
        );
    }

    @Test
    public void differentFormatsAreNotEqual() {
        MatcherAssert.assertThat(
            new Format.FormatEq(new FormatFor(10, "9")),
            new IsNot<>(new IsEqual<>(new FormatFor(12, "8")))
        );
    }

    @Test
    public void notEqualsString() {
        MatcherAssert.assertThat(
            new Format.FormatEq(new FormatFor(5, "45")),
            new IsNot<>(new IsEqual<>("hi"))
        );
    }

    @Test
    public void notEqualsNull() {
        MatcherAssert.assertThat(
            new Format.FormatEq(new FormatFor(8, "41")),
            new IsNot<>(new IsEqual<>(null))
        );
    }

    @Test
    public void calcHashCorrectly() {
        MatcherAssert.assertThat(
            new Format.FormatEq(new FormatFor(11, "47")).hashCode(),
            new IsEqual<>(Objects.hash(11, "47"))
        );
    }
}
