/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Phone number format: required length and prefix. Prefix is actually
 * country code.
 * @since 1.0
 */
final class Format {

    /**
     * Phone length.
     */
    private final int len;

    /**
     * Phone prefix, can be an empty string.
     */
    private final String prefix;

    /**
     * Ctor.
     * @param len Format length
     * @param prefix Format prefix
     */
    Format(final int len, @NotNull final String prefix) {
        this.len = len;
        this.prefix = prefix;
    }

    @Override
    public boolean equals(final Object obj) {
        final boolean res;
        if (obj == null || getClass() != obj.getClass()) {
            res = false;
        } else {
            res = this == obj || this.len == ((Format) obj).len
                && Objects.equals(this.prefix, ((Format) obj).prefix);
        }
        return res;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.len, this.prefix);
    }
}
