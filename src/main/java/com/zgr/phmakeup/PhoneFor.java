/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

/**
 * Phone for number and profile.
 * @since 1.0
 */
public final class PhoneFor implements Phone {
    /**
     * Phone number.
     */
    private final String number;

    /**
     * Profile.
     */
    private final Profile profile;

    /**
     * Ctor.
     * @param number Phone number
     * @param profile Phone profile
     */
    public PhoneFor(final String number, final Profile profile) {
        this.number = number;
        this.profile = profile;
    }

    @Override
    public String makeup() {
        final String clean = this.number.replaceAll("[^\\d]", "");
        for (final Format format : this.profile.formats()) {
            if (format.suits(clean)) {
                return format.format(clean);
            }
        }
        throw new IllegalArgumentException(
            String.format(
                "Number %s does not correspond to given profile",
                this.number
            )
        );
    }
}
