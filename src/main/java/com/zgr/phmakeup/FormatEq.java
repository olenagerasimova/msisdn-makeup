/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Implementation of {@link Format} with equals. Useful in tests.
 * @since 1.0
 */
final class FormatEq implements Format {

    /**
     * Origin format.
     */
    private final Format origin;

    /**
     * Ctor.
     * @param origin Format
     */
    FormatEq(final Format origin) {
        this.origin = origin;
    }

    @Override
    public @NotNull String prefix() {
        return this.origin.prefix();
    }

    @Override
    public int len() {
        return this.origin.len();
    }

    @Override
    public boolean suits(final String number) {
        return this.origin.suits(number);
    }

    @Override
    public String format(final String number) {
        return this.origin.format(number);
    }

    @Override
    public boolean equals(final Object that) {
        return that instanceof Format
            && (this == that || this.len() == ((Format) that).len()
            && Objects.equals(this.prefix(), ((Format) that).prefix()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.len(), this.prefix());
    }
}
