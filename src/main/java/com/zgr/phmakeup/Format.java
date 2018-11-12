/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import javax.validation.constraints.NotNull;

/**
 * Phone (msisdn) format: country code and length.
 * @since 1.0
 */
public interface Format {

    /**
     * Format, that checks only maximum length.
     * @checkstyle AnonInnerLengthCheck (30 lines)
     */
    Format NO_CHECK = new Format() {
        @Override
        public @NotNull String prefix() {
            return "";
        }

        @Override
        public int len() {
            //@checkstyle MagicNumberCheck (1 line)
            return 15;
        }

        @Override
        public boolean suits(@NotNull final String number) {
            return number.length() <= this.len();
        }

        @Override
        public @NotNull String format(@NotNull final String number) {
            if (!this.suits(number)) {
                throw new IllegalArgumentException(
                    String.format(
                        "Msisdn %s is longer than allowed (max allowed is %d)",
                        number, this.len()
                    )
                );
            }
            return number;
        }
    };

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
     * Checks whether given phone suits to the format.
     * @param number Clean phone
     * @return True if number suits this format
     */
    boolean suits(@NotNull String number);

    /**
     * Formats given number according to formats prefix and length.
     * @param number Clean phone
     * @return Formatted msisdn
     * @throws IllegalArgumentException If given number cannot be formatted
     */
    @NotNull String format(@NotNull String number);

}
