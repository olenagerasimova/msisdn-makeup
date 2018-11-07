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
 * Test for {@link Format}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public class FormatTest {

    @Test
    public void equalsItself() {
        final Format frmt = new Format(11, "7");
        MatcherAssert.assertThat(
            frmt,
            new IsEqual<>(frmt)
        );
    }

    @Test
    public void sameObjectsAreEqual() {
        MatcherAssert.assertThat(
            new Format(12, "8"),
            new IsEqual<>(new Format(12, "8"))
        );
    }

    @Test
    public void notEqualsString() {
        MatcherAssert.assertThat(
            new Format(5, "45"),
            new IsNot<>(new IsEqual<>("hi"))
        );
    }

    @Test
    public void notEqualsNull() {
        MatcherAssert.assertThat(
            new Format(8, "41"),
            new IsNot<>(new IsEqual<>(null))
        );
    }

    @Test
    public void calcHashCorrectly() {
        MatcherAssert.assertThat(
            new Format(11, "47").hashCode(),
            new IsEqual<>(Objects.hash(11, "47"))
        );
    }
}
