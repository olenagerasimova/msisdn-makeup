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
 * Test for {@link Phone.Simple}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle JavadocVariableCheck (500 lines)
 */
public class SimplePhoneTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void makeupsPhone() {
        MatcherAssert.assertThat(
            new Phone.Simple("7(909) 695-92-12").makeup(),
            new IsEqual<>("79096959212")
        );
    }

    @Test
    public void doesNothingIfPhoneAlreadyOk() {
        final String phone = "4595874569654";
        MatcherAssert.assertThat(
            new Phone.Simple(phone).makeup(),
            new IsEqual<>(phone)
        );
    }

    @Test
    public void failsIfPhoneIsTooLong() {
        final String phone = "456 (458) 458-547-898-99";
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage(
            String.format(
                "Msisdn %s is longer than allowed (max allowed is %d)",
                phone, Profile.UNLIMITED.formats().get(0).len()
            )
        );
        new Phone.Simple(phone).makeup();
    }
}
