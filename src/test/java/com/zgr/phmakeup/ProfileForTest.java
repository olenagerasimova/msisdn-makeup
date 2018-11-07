/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import org.cactoos.list.ListOf;
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
    public void parsesShortDescriptorCorrectly() {
        MatcherAssert.assertThat(
            new ProfileFor("13,77").formats(),
            new IsIterableContainingInAnyOrder<>(
                new ListOf<Matcher<? super Object>>(
                    new IsEqual<>(new Format(13, "77"))
                )
            )
        );
    }

    @Test
    public void parsesLongDescriptorCorrectly() {
        MatcherAssert.assertThat(
            new ProfileFor("13,71;11,7;15,789;12;9,0").formats(),
            new IsIterableContainingInAnyOrder<>(
                new ListOf<Matcher<? super Object>>(
                    new IsEqual<>(new Format(13, "71")),
                    new IsEqual<>(new Format(11, "7")),
                    new IsEqual<>(new Format(15, "789")),
                    new IsEqual<>(new Format(12, "")),
                    new IsEqual<>(new Format(9, "0"))
                )
            )
        );
    }
}
