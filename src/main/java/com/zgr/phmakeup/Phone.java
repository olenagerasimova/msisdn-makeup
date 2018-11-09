/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

/**
 * Phone number (msisdn).
 * @since 1.0
 */
public interface Phone {

    /**
     * Makeups phone numbers.
     * @return Clean phone number.
     * @throws IllegalArgumentException if makeup is not possible
     */
    String makeup();

    /**
     * Simple implementation of {@link Phone}, cleans phones according to
     * {@link Profile#UNLIMITED} format.
     */
    class Simple implements Phone {
        /**
         * Phone.
         */
        private final String phone;

        /**
         * Ctor.
         * @param phone Msisdn
         */
        public Simple(final String phone) {
            this.phone = phone;
        }

        @Override
        public String makeup() {
            final String clean = this.phone.replaceAll("[^\\d]", "");
            if (clean.length() > Profile.UNLIMITED.formats().get(0).len()) {
                throw new IllegalArgumentException(
                    String.format(
                        "Msisdn %s is longer than allowed (max allowed is %d)",
                        this.phone, Profile.UNLIMITED.formats().get(0).len()
                    )
                );
            }
            return clean;
        }
    }
}
