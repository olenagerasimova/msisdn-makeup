/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test for {@link Profile#RU} and {@link Profile#UNLIMITED}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public class ProfileTest {

    @Test
    public void unlimitedIsCorrect() {
        MatcherAssert.assertThat(
            Profile.UNLIMITED.formats(),
            new IsCollectionWithSize<>(new IsEqual<>(1))
        );
        MatcherAssert.assertThat(
            new Format.FormatEq(Profile.UNLIMITED.formats().get(0)),
            new IsEqual<>(new FormatFor(15, ""))
        );
    }

    @Test
    public void ruProfileIsCorrect() {
        MatcherAssert.assertThat(
            Profile.RU.formats(),
            new IsCollectionWithSize<>(new IsEqual<>(1))
        );
        MatcherAssert.assertThat(
            new Format.FormatEq(Profile.RU.formats().get(0)),
            new IsEqual<>(new FormatFor(11, "7"))
        );
    }
}
