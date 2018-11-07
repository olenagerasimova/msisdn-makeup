/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
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
            new IsIterableContainingInAnyOrder<>(
                new ListOf<>(
                    new IsEqual<>(new Format(15, ""))
                )
            )
        );
    }

    @Test
    public void ruProfileIsCorrect() {
        MatcherAssert.assertThat(
            Profile.RU.formats(),
            new IsIterableContainingInAnyOrder<>(
                new ListOf<>(
                    new IsEqual<>(new Format(11, "7"))
                )
            )
        );
    }
}
