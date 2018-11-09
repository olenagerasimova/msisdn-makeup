/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Phone (msisdn) format: country code and length.
 * @since 1.0
 */
public interface Format {

    /**
     * Format prefix (country code).
     * @return String prefix
     */
    @NotNull String prefix();

    /**
     * Format's length (prefix included).
     * @return Int length
     */
    int len();

    /**
     * Russian phone numbers format descriptor.
     */
    class Russian implements Format {

        @Override
        public @NotNull String prefix() {
            return "7";
        }

        @Override
        public int len() {
            //@checkstyle MagicNumberCheck (1 line)
            return 11;
        }
    }

    /**
     * Implementation of {@link Format} with equals. Useful in tests.
     */
    class FormatEq implements Format {

        /**
         * Origin format.
         */
        private final Format origin;

        /**
         * Ctor.
         * @param origin Format
         */
        public FormatEq(final Format origin) {
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
}
