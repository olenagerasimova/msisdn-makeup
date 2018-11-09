/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test for {@link Format.Russian}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public class RussianFormatTest {

    @Test
    public void returnsCorrectPrefix() {
        MatcherAssert.assertThat(
            new Format.FormatEq(new Format.Russian()),
            new IsEqual<>(new FormatFor(11, "7"))
        );
    }
}
