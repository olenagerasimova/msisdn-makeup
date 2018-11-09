/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import javax.validation.constraints.NotNull;

/**
 * Phone number format: required length and prefix. Prefix is actually
 * country code.
 * @since 1.0
 */
final class FormatFor implements Format {

    /**
     * Phone length.
     */
    private final int lngth;

    /**
     * Phone prefix, can be an empty string.
     */
    private final String prfx;

    /**
     * Ctor.
     * @param len FormatFor length
     * @param prefix FormatFor prefix
     */
    FormatFor(final int len, @NotNull final String prefix) {
        this.lngth = len;
        this.prfx = prefix;
    }

    @Override
    public String prefix() {
        return this.prfx;
    }

    @Override
    public int len() {
        return this.lngth;
    }
}
